public class Lectuer {
    String subject;
    TimeLectuer timeLectuer;// from hour to hour
    DurationLectuer durationLectuer;//hour
    String palce;
    GroupStudent groupStudent;

    public Lectuer(String subject, TimeLectuer timeLectuer, DurationLectuer durationLectuer, String palce, GroupStudent groupStudent) {
        this.subject = subject;
        this.timeLectuer = timeLectuer;
        this.durationLectuer = durationLectuer;
        this.palce = palce;
        this.groupStudent = groupStudent;
    }

    public Lectuer(String subject, GroupStudent groupStudent) {
        this.subject = subject;
        this.groupStudent = groupStudent;
    }

    public boolean isequal(Lectuer l){
        if(l.groupStudent.theoretical&&l.subject.equals(this.subject))return true;
        if(l.subject.equals(this.subject)&&l.groupStudent.name.equals(this.groupStudent.name))return true;


        // same time and palce
        if ((l.timeLectuer.start<this.timeLectuer.end&&l.timeLectuer.start>this.timeLectuer.start)||
                (l.timeLectuer.end>this.timeLectuer.start&&l.timeLectuer.end<this.timeLectuer.end)){
            if (l.palce.equals(this.palce))return true;
        }
        return false;

    }
}
