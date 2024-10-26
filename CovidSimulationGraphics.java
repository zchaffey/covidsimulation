
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;


public class CovidSimulationGraphics  extends JPanel
{
     
     int index;
     boolean sstartsim,eendsim,rrecovered,aalive;
     ArrayList<People> ppeople;
     GameBorder ggb;
     String nni,nnh,nnd,nnr;
    public CovidSimulationGraphics(boolean recovered,boolean alive, boolean startsim,boolean endsim,ArrayList<People> people,GameBorder gb,String nr,String ni,String nh,String nd)               
  {
        sstartsim=startsim;
        eendsim=endsim;
        rrecovered=recovered;
        aalive=alive;
        ppeople=people;
        ggb=gb;
        nni=ni;
        nnh=nh;
        nnd=nd;
        nnr=nr;
        
        setBackground(Color.black);
    }
    public void UpdateStrings(String nr,String ni,String nd,String nh)
    {
        nni=ni;
        nnh=nh;
        nnd=nd;
        nnr=nr;
    }
    
    
    public void paint (Graphics g)         
    {
        super.paint(g);
     
        g.setColor(new Color(0,102,0));
        
        g.fillRect(ggb.getXStart(),ggb.getYStart(),ggb.getXDimension(),ggb.getYDimension());
   
    for(index=0; index <= ppeople.size()-1; index++)
        {
           if (ppeople.get(index).getInfected()== true)
         {
             g.setColor(Color.red);
            }
         if(ppeople.get(index).getHealthy() == true)
         {  
             g.setColor(Color.green);
            } 
            if(ppeople.get(index).getDead()==true)
         {
              g.setColor(Color.black);
           }
            if (ppeople.get(index).getRecovered() == true)
         {
             g.setColor(Color.blue);
           }
            g.fillOval(ppeople.get(index).getX(),ppeople.get(index).getY(),10,10);
           
          }
          g.setColor(Color.white);
        g.drawString(nnh,75,650);
        g.drawString(nni,275,650);
        g.drawString(nnd,475,650);
        g.drawString(nnr,675,650);
     }    
        
}
