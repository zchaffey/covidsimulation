

public class People
{
    int x,y,xdir,ydir,randomdir,numberofsteps,stepstaken,infectionchance,infectiontime,dyingchance;
    boolean setnewdirection,infected,dead,canmove,recovered,healthy,PersonRecovered;
     
    public People(int xx, int yy)
     {
      x = xx;
      y = yy;
      infected=false;
      dead=false;
      canmove=true;
      recovered=false;
      healthy=true;
      PersonRecovered=false;
      infectiontime=0;
      
     }

    public int getX()
    {
        return (int)x;
    }
     public int getY()
    {
        return (int)y;
    }
     public boolean getInfected()
    {
         return infected;
      }
    public void setInfected(boolean inf)
    {
         infected = inf;
      }
    public int getInfectionChance()
    {
        return (int)infectionchance;
    }
    public boolean getDead()
    {
        return dead;
    }
    public boolean getRecovered()
    {
        return recovered;
    }
    public void setRecovered(boolean rec)
    {
        recovered=rec;
    }
    public boolean getHealthy()
    {
        return healthy;
    }
    public void setHealthy(boolean health)
    {
        healthy=health;
    }
    public int getInfectionTime()
    {
        return infectiontime;
    }
    public boolean getPersonRecovered()
    {
        return PersonRecovered;
    }
    public void setPersonRecovered(boolean pere)
    {
        PersonRecovered=pere;
    }
    public void movePeople(GameBorder gb)
    {
        if (canmove==true)
        {
        x = x + xdir;
        y = y + ydir;
    }
        if(infected == true)
        {
            infectiontime++;
        }
      
        if(infected == true && infectiontime > 500)
        {
            SurvivalChance();
        }
      
      if(x< gb.getXStart())
      {
         x=gb.getXStart();
         stepstaken=numberofsteps+1;
        }
      if(x> gb.getXStart() + gb.getXDimension() - 10)
        { x = gb.getXStart() + gb.getXDimension() - 10;
          stepstaken=numberofsteps+1;
        }
      if(y< gb.getYStart())
        { y=gb.getYStart();
          stepstaken=numberofsteps+1;
        }
      if(y> gb.getYStart() + gb.getYDimension() - 10)
        { y = gb.getYStart() + gb.getYDimension() - 10;
          stepstaken=numberofsteps+1;
        }
       
    }
    public void changedirection(GameBorder gb)
    {
       movePeople(gb);
       stepstaken++;
       if(stepstaken > numberofsteps)
      {
          setnewdirection=true;
        }
      if(setnewdirection==true)
     {
        setnewdirection=false;
        stepstaken=0;
        randomdir=(int)(Math.random()*8+1);
        if(randomdir==1)
      {
          xdir=-1;
          ydir=0;
        }
      if(randomdir==2)
      { 
          xdir=1;
          ydir=0;
           }
      if(randomdir==3)
      {
          ydir=1;
          xdir=0;
        }
      if(randomdir==4)
      {
          ydir=-1;
          xdir=0;
        }
      if(randomdir==5)
       { 
          xdir=-1;
          ydir=-1;
               }
      if(randomdir==6)
       {
        xdir=1;
        ydir=-1;
               }
      if(randomdir==7)
         {
          xdir=1;
          ydir=1;
               }
      if(randomdir==8)
      {
       xdir=-1;
       ydir=1;
               }
   
      numberofsteps= (int)(Math.random()*100+50);
    }
      
  
    }
      public void InfectionChance()
    {
      infectionchance= (int)(Math.random()*10+1);
        if(infectionchance<=9)
      {
       infected=true;
       healthy=false;
               }
      
     }
       public void SurvivalChance()
    {
          dyingchance = (int)(Math.random()*100+1);
          if (dyingchance>=96)
        {
            dead = true;
            infected=false;
            canmove=false;
            healthy=false;
            recovered=false;
        }
          if (dyingchance<=95)
          {
              recovered=true;
              infected=false;
              canmove=true;
              healthy=false;
              dead=false;
            }

      }
}
