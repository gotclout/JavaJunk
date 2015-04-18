


import javax.swing.JOptionPane;
//title of the lab assignment
public class program2
{
  int select;
  int line = 0;
  int linetotal;
  double haircut = 20.0;
  double hairstyle = 35.0;
  double haircolor = 50.0;
  double tip = 0.0;

  public static void main (String [] arags) {

    //This loop prompts the user to enter the number of people in line with a max of 20 if. If more than 20
    //entered, than it returns them to the input menu.
    do
    {
      while(line = 0)
      {
        line = Integer.parseInt(JOptionPane.showInputDialog(null,"How many people are waiting in line?" , "Input"));
      }
      if (line >= 20)
        line = JOptionPane.showMessageDialog(null, "Error!!! There may be no more than 20 people waiting in the line.");
    }

    do{
      select = Integer.parseInt(JOptionPane.showInputDialog(null, "What service would customer #" + line +  "1. Haircut only($20.)\n 2. Haircut and Hair Style ($35.0)\n 3. Haircut and Haircolor ($50.0)\n\nEnter Choice:" , "Input"));
      while(line <= 20);
    }
    switch(select){
      // Select Haircut
      case 1:
        {
          haircut = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the amount of tip to be given to your stylist. \n(Recommended: 10%:$2.0; 15%: 3.0; 20%: $4.0):$", "Input"));}
    }}
  break;
  //Select Hairstyle
  case 2:
  {
    haircut = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the amount of tip to be given to your stylist. \n(Recommended: 10%: $3.50; 15%: $5.25; 20%: $7.0):$","Input"));}
}
break;
//Select Haircut and Hair Color
case 3:
{
  haircolor = Double.parseDouble(JOptionPane.showInputDialog(null,"Enter the amount of tip to be given to your stylist. \n(Recommended: 10%: $5; 15%: $7.5; 20%: $10):$","Input"));
}
break;
}
