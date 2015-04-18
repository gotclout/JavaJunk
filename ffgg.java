import java.util.*;
import java.io.*;

class ffgg
{
  public enum success{
    OK,
      ERROR,
      FAIL
  }

  public static class state
  {
    boolean   farmer,
              fox,
              goose,
              grain;

    state()
    {
      farmer = fox = goose = grain = true;
    }
    void emptyState()
    {
      farmer = fox = goose = grain = false;
    }
  }

  public static class mainStates
  {
    state begin,
          end;
    success retVal;

    mainStates()
    {
      begin = new state();
      end = new state();
      end.emptyState();
    }
    success sendAcross(String entity)
    {
      success retVal = success.OK;

      begin.farmer = false;
      end.farmer = true;

      if(entity.equals("fox"))
      {
        if(begin.fox)
        {
          begin.fox = false;
          end.fox = true;
        }
        else
        {
          System.out.println("The fox is not on this side of the river");
          retVal = success.ERROR;
        }
      }
      else if( entity.equals("goose"))
      {
        if(begin.goose)
        {
          begin.goose = false;
          end.goose = true;
        }
        else
        {
          System.out.println("The goose is not on this side of the river");
          retVal = success.ERROR;
        }
      }
      else if(entity.equals("grain"))
      {
        if(begin.grain)
        {
          begin.grain = false;
          end.grain = true;
        }
        else
        {
          System.out.println("The grain is not on this side of the river");
          retVal = success.ERROR;
        }
      }

      if(begin.fox && begin.goose & !begin.farmer)
      {
        System.out.println("The fox ate the goose\n");
        retVal = success.FAIL;
      }
      else if(begin.goose && begin.grain && !begin.farmer)
      {
        System.out.println("The goose ate the grain\n");
        retVal = success.FAIL;
      }

      return retVal;
    }

    success sendBack(String entity)
    {
      success retVal = success.OK;

      begin.farmer = true;
      end.farmer = false;

      if(entity.equals("fox"))
      {
        if(end.fox)
        {
          end.fox = false;
          begin.fox = true;
        }
        else
        {
          System.out.println("The fox is not on this side of the river\n");
          retVal = success.ERROR;
        }
      }
      else if(entity.equals("goose"))
      {
        if(end.goose)
        {
          end.goose = false;
          begin.goose = true;
        }
        else
        {
          System.out.println("The goose is not on this side of the river\n");
          retVal = success.ERROR;
        }
      }
      else if(entity.equals("grain"))
      {
        if(end.grain)
        {
          end.grain = false;
          begin.grain = true;
        }
        else
        {
          System.out.println("The grain is not on this side of the river\n");
          retVal = success.ERROR;
        }
      }
      if(end.fox && end.goose && !end.farmer)
      {
        System.out.println("The fox ate the goose\n");
        retVal = success.FAIL;
      }
      else if(end.goose && end.grain && !end.farmer)
      {
        System.out.println("The goose ate the grain\n");
        retVal = success.FAIL;
      }
      else
      {
        begin.farmer = true;
        end.farmer = false;
      }

      return retVal;
    }
  }

  public static void main(String [] args)
  {
    InputStreamReader isr = new InputStreamReader(System.in);
    mainStates setUp = new mainStates();

    success ok;
    boolean proceed = true;

    String entity = "";

    System.out.println(
        "You may quit this application at any time by entering exit\n");
    System.out.println(
        "The farmer, fox, goose, and grain need to be moved accross the river\n");
    System.out.println(
        "The farmer can take one item accross the river at a time in either direction\n");
    System.out.println(
        "Enter the name of the object you want the farmer to take accross\n");

    try{
      BufferedReader cin = new BufferedReader(isr);
      entity = cin.readLine();
      while(!entity.equals("exit"))
      {
        while(!entity.equals("fox") && !entity.equals("goose")
                                    && !entity.equals("grain"))
        {
          if(entity.equals("exit"))
          {
            proceed = false;
            break;
          }
          else if(entity.equals("none"))
          {
            System.out.println(
                "Enter the name of the next entity you want the farmer to take across\n");
            entity = cin.readLine();
          }
          else
          {
            System.out.println("Please enter a valid entity\n");
            entity = cin.readLine();
          }
        }

        if(proceed)
        {
          ok = setUp.sendAcross(entity);
          if(ok == success.OK)
          {
            if(setUp.end.farmer && setUp.end.fox && setUp.end.goose && setUp.end.grain)
            {
              System.out.println("Congratulations, you have solved the puzzle\n");
              entity = "exit";
            }
            else
            {
              System.out.println(
                  "Enter the name of the object you want the farmer to take back if any, else enter none\n");
              entity = cin.readLine();
              while(!entity.equals("fox") && !entity.equals("goose") &&
                    !entity.equals("grain") && !entity.equals("none"))
              {
                if(entity.equals("exit"))
                {
                  proceed = false;
                  break;
                }
                else
                {
                  System.out.println("Please enter a valid entity\n");
                  entity = cin.readLine();
                }
              }
              if(proceed)
              {
                ok = setUp.sendBack(entity);
                if( ok == success.OK)
                {
                  if(setUp.end.farmer && setUp.end.fox &&
                     setUp.end.goose && setUp.end.grain)
                  {
                    System.out.println("Congratulations, you have solved the puzzle\n");
                    entity = "exit";
                  }
                  entity = "none";
                }
                else if(ok == success.ERROR)
                {
                  System.out.println("Please enter a valid entity\n");
                  entity = cin.readLine();
                }
                else if(ok == success.FAIL)
                {
                  System.out.println("Game over :-( \n");
                  entity = "exit";
                }
              }
            }
          }
          else if(ok == success.ERROR)
          {
            System.out.println("Please enter a valid entity\n");
            entity = cin.readLine();
          }
          else if(ok == success.FAIL)
          {
            System.out.println("Game over :-(\n");
            entity = "exit";
          }
        }
      }
    }
    catch (IOException e)
    {
      System.err.println("Error: " + e);
    }
  }
}

