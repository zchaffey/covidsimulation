
/** Covid Simulation
 *   @author (Zack Chaffey)
 *    @version (10/27/20)
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class CovidSimulation implements ActionListener
{
        JFrame f1;
        JPanel main,sub;
        CovidSimulationGraphics g1;
        int timeinfected,index,stepstaken,randomdir,xdir,ydir,i,numberhealthy,numberdead,numberrecovered,numberinfected,endsimtimer;
        JButton b1,b2,b3;
        boolean recovered,alive,startsim,endsim;
        ArrayList<People> people;
        GameBorder gb;
        String nr,ni,nh,nd;
      
        



    public CovidSimulation()
    {
        setinitialvalues();
        people = new ArrayList<People>();
        gb= new GameBorder(40,20,800,600);
        
        f1 = new JFrame("Covid Simulation");
          f1.setSize(900,750);
          f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          f1.setResizable(false);
         
        
        Container c1 = f1.getContentPane();
        
        b1=new JButton("Run Simulation");
        b1.addActionListener(this);
        b1.setBackground(Color.white);
        b2=new JButton("Reset");
        b2.addActionListener(this);
        b2.setBackground(Color.white);
        b3=new JButton("Self Quarantine-Off");
        b3.addActionListener(this);
        b3.setBackground(Color.white);

        
        nr="";
        nh="";
        nd="";
        ni="";

        g1 = new CovidSimulationGraphics(recovered,alive,startsim,endsim,people,gb,nr,ni,nh,nd);
         UpdateLabels();
         sub = new JPanel(); 
          sub.add(b1);
          sub.add(b2);
          sub.add(b3);
          sub.setBackground(Color.black);
                                      
        main = new JPanel();
          main.setLayout(new BorderLayout());    
          main.setSize(900,700);
          main.add(g1,BorderLayout.CENTER);
          main.add(sub,BorderLayout.SOUTH);
          
        
        
        makepeople();
         
        c1.add(main);
        
        f1.show();
        
        runSim();
        
        
    }
    private void setinitialvalues()
    {
        startsim=false;
        endsim=false;
        alive=true;
        recovered=false;

        numberinfected=1;
        numberhealthy=199;
        numberdead=0;
        numberrecovered=0;
      
       }
    private void makepeople()
    {
        for (index =0; index < 200; index++)
         {  
            people.add(new People((int)(Math.random()*790+40),(int)(Math.random()*590+20)));
           
            }  
         people.get(0).setInfected(true);
         people.get(0).setHealthy(false);
       }
    public void runSim()
    {
        Thread runner = new Thread();
        while (endsim == false)
     {
         
        try 
            { 
               runner.sleep(10); 
             }
            catch(InterruptedException e) {}  
        if (startsim == true)
        {
            
            for (index=0; index < 200; index++)
             {
            people.get(index).changedirection(gb);
            g1.repaint();
             }
            
            for(index=0; index < 200; index++)
          {
             for(i=0; i < 200; i++)
             {
              if(i != index)
              
               if(people.get(index).getX() > people.get(i).getX() -5 && people.get(index).getX() < people.get(i).getX() +5 && people.get(index).getY() > people.get(i).getY() -5 && people.get(index).getY() < people.get(i).getY() +5 )
                 if (people.get(i).getInfected() == true && people.get(index).getInfected() ==false && people.get(i).getDead() == false && people.get(index).getDead() == false && people.get(i).getRecovered()==false && people.get(index).getRecovered()== false) 
                 {
                   people.get(index).InfectionChance();
                   if(people.get(index).getInfected() == true || people.get(index).getDead()==true)
                   {
                       numberinfected++;
                       numberhealthy--;
                    }
                   UpdateLabels();
                }
                
                }
               
             if ( people.get(index).getInfectionTime() > 500  && people.get(index).getPersonRecovered() == false )
                {
                     UpdateRecoveredDead();
                     people.get(index).setPersonRecovered(true); 
                }
                
                }
     
            
            if ( (numberrecovered+numberdead== 200) || (numberinfected==0) )
               endsim=true;
           
            endsimtimer++;
            if(endsimtimer==3250)
            {
                endsim=true;
            }
           
       }
           
       }
      }
      public void UpdateRecoveredDead()
      {
          if (people.get(index).getRecovered() == true)
        {
                numberrecovered++;
                numberinfected--;
            }
           if (people.get(index).getDead() == true)
        {
                numberdead++;
                numberinfected--;
          }
    
          UpdateLabels();
        
    }
    public void UpdateLabels()
    {
        
        nr=new String("Number Recovered:  "+numberrecovered);
        ni=new String("Number Infected:  "+numberinfected);
        nd=new String("Number Dead:  "+numberdead);
        nh=new String("Number Healthy:  "+numberhealthy);
        g1.UpdateStrings(nr,ni,nd,nh);
    }
    public void actionPerformed (ActionEvent event)
    {
         
        if (event.getSource() == b1)
        {
            startsim=true;
            if(b3.getText().equals("Self Quarantine-On"))
            {
                startsim=false;
               }
            
            g1.requestFocus();
         }
         
        if (event.getSource() == b2)
        {
           startsim=false;
           people.clear();
           makepeople();
           g1.repaint();
           b3.setText("Self Quarantine-Off");
           numberrecovered=0;
           numberdead=0;
           numberhealthy=199;
           numberinfected=1;
           UpdateLabels();
           }
        if (event.getSource() == b3)
        {
           if(b3.getText().equals("Self Quarantine-Off"))
           {
              b3.setText("Self Quarantine-On");
              startsim=false;
           }
           else
           {
              b3.setText("Self Quarantine-Off");
              startsim=true;
           }
         }
         
      }
    
}
