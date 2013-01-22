/**
 * Subnetter back end
 * User: Robin Stark
 * Date: 1/21/13
 * Time: 1:54 AM
 * Purpose: This calculates the range, decrement octet, and interval of any valid subnet mask
 * Variables:
 *      mask - this is the ipv4 mask
 *      Interval - this is the interval between the subnets
 *      maskRanges - where the subnets start and end
 *      boundary - which octet is the decrement in
 */
public class BackEnd {
    public final int NUM_OF_OCTETS=4;
    private int[] mask;
    private int interval;
    public int[][] maskRanges;
    private int boundary;
    private boolean calculated;
    int numRanges;
    //Constructors
    //Passing an IP mask
    public BackEnd ()
    {
        mask = null;
        interval = -1;
        maskRanges=null;
        boundary=-1;
        calculated=false;
        numRanges=-1;
    }
    public BackEnd (int[] a)
    {
        boolean valid = true;
        //Check to make sure it's valid
        for (int i=0; i < NUM_OF_OCTETS; i++)
        {
            if (a[i] > 255 || a[i] < 0)
            {
                //Invalid mask
                mask=null;
                valid=false;
                break;
            }
        }
        //If the IPv4 mask is valid, assign it
        if(valid)
        {
            mask = new int[NUM_OF_OCTETS];
            mask=a;
        }
        //We don't know any of this info yet
        interval=-1;
        maskRanges=null;
        boundary =-1;
        calculated=false;
        numRanges=0;
    }

    //Getters
    public int[] getmask(){return mask;}
    public int getInterval() {return interval;}
    public int[][] getmaskRanges() {return maskRanges;}
    public boolean getCalculated () {return calculated;}
    public int getBoundary() {return boundary;}


    //Methods start here
    //Calculate Subnet
    public void Calculate (int[] m)
    {
        boolean valid = true;
        //Check to make sure it's valid
        for (int i=0; i < NUM_OF_OCTETS; i++)
        {
            if (m[i] > 255 || m[i] < 0)
            {
                //Invalid mask
                mask=null;
                valid=false;
                break;
            }
        }
        //If the IPv4 mask is valid, assign it
        if(valid)
        {
            mask = new int[NUM_OF_OCTETS];
            mask=m;
        }
        //Check to make sure the mask is valid
        if (mask!=null)
        {
            setBoundary();
            setInterval();
            setmaskRanges();
        }

        //Find the maskRanges
    }

    //Find Octet and set boundary
    private void setBoundary()
    {
        //Check to make sure the mask is valid
        if (mask!=null)
        {
            for (int i=0; i < NUM_OF_OCTETS; i++)
            {
                if(mask[i]!= 255)
                {
                    boundary =i;
                    break;
                }
            }
        }
    }
    private void setInterval()
    {
        //Check to make sure that setBoundary has been run
        if (boundary !=-1)
        {
            interval=256-mask[boundary];
        }
    }
    private void setmaskRanges()
    {
        if (interval==-1) {return;}

        for(int i=0; i < 255;i+=interval )
        {
            numRanges++;
        }
        numRanges++;
        maskRanges = new int[numRanges+1][numRanges+1];
        numRanges=0;
        for (int i=0; i < 255; i+=interval)
        {
            maskRanges[numRanges][0]=i;
            maskRanges[numRanges][1]=i+interval-1;
            numRanges++;
        }
    }
}
