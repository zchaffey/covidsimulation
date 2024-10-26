

public class GameBorder
{
    int xstart, ystart, xdimension, ydimension;
   
    
    public GameBorder(int xs, int ys,  int xd, int yd)
    {
       xstart = xs;
       ystart = ys;
       xdimension = xd;
       ydimension = yd; 
    }

    public int getXStart()
    {
        return xstart;
    }
    
    public int getYStart()
    {
        return ystart;
    }
    
     public int getXDimension()
    {
        return xdimension;
    }
    
    public int getYDimension()
    {
        return ydimension;
    }

}
