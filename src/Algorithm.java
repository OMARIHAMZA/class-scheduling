import java.util.ArrayList;

public class Algorithm {
    ArrayList<Lectuer> Staticlectuers;




    private void generatStaticLectures(){
        // theoretical
        this.Staticlectuers.add(new Lectuer("Theoretical_SW1",new GroupStudent(true)));
        this.Staticlectuers.add(new Lectuer("Theoretical_DB2",new GroupStudent(true)));
        this.Staticlectuers.add(new Lectuer("Theoretical_OS",new GroupStudent(true)));
        this.Staticlectuers.add(new Lectuer("Theoretical_AI",new GroupStudent(true)));
        this.Staticlectuers.add(new Lectuer("Theoretical_CD",new GroupStudent(true)));
        this.Staticlectuers.add(new Lectuer("Theoretical_EY",new GroupStudent(true)));

        this.Staticlectuers.add(new Lectuer("EY",new GroupStudent("ALL")));

        this.Staticlectuers.add(new Lectuer("CD",new GroupStudent("M1")));
        this.Staticlectuers.add(new Lectuer("CD",new GroupStudent("M2")));

        this.Staticlectuers.add(new Lectuer("AI",new GroupStudent("F1")));
        this.Staticlectuers.add(new Lectuer("AI",new GroupStudent("F2")));
        this.Staticlectuers.add(new Lectuer("AI",new GroupStudent("F3")));
        this.Staticlectuers.add(new Lectuer("AI",new GroupStudent("F4")));

        this.Staticlectuers.add(new Lectuer("OS",new GroupStudent("F1")));
        this.Staticlectuers.add(new Lectuer("OS",new GroupStudent("F2")));
        this.Staticlectuers.add(new Lectuer("OS",new GroupStudent("F3")));
        this.Staticlectuers.add(new Lectuer("OS",new GroupStudent("F4")));

        this.Staticlectuers.add(new Lectuer("DB2",new GroupStudent("F1")));
        this.Staticlectuers.add(new Lectuer("DB2",new GroupStudent("F2")));
        this.Staticlectuers.add(new Lectuer("DB2",new GroupStudent("F3")));
        this.Staticlectuers.add(new Lectuer("DB2",new GroupStudent("F4")));

        this.Staticlectuers.add(new Lectuer("SW1",new GroupStudent("M1")));
        this.Staticlectuers.add(new Lectuer("SW1",new GroupStudent("M2")));

    }
}
