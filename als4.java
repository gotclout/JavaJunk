import javax.swing.JOptionPane;

public class Labassignment4{

  public static void main (String [] args){

    //Patient Name
    String patientstring = JOptionPane.showInputDialog(null,"Enter patient name:");

    //Patient Age as String
    String agestring = JOptionPane.showInputDialog(null, "How old is " + patientstring);

    //Patient Age as int
    int age = Integer.parseInt(agestring);

    //Wears Glasses
    int wearsGlasses = JOptionPane.showConfirmDialog(null, patientstring + " does NOT wear glasses");

    //Last Visit as String
    String lastVisitString = JOptionPane.showInputDialog(null, "How many years since " + patientstring + " had a vision exam?");

    //Last Visit as int
    int visit = Integer.parseInt(lastVisitString);

    //coverage
    boolean covered = false;
    if(age <= 18){
      if(wearsGlasses == JOptionPane.YES_OPTION && visit > 2){
        covered = true;
      }
      else if(visit > 4){
        covered = true;
      }
    }
    else if(age <= 44){
      if(wearsGlasses == JOptionPane.YES_OPTION && visit > 1){
        covered = true;
      }
      else if(visit > 2){
        covered = true;
      }
    }
    else if(age <= 110){
      if(wearsGlasses == JOptionPane.YES_OPTION && visit > 1){
        covered = true;
      }
      else if(visit > 1){
        covered = true;
      }
    }

    if(covered){
      JOptionPane.showMessageDialog(null, "Patient is covered", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
    else{
      JOptionPane.showMessageDialog(null, "Patient is not covered", "Message", JOptionPane.INFORMATION_MESSAGE);
    }
  }
}

