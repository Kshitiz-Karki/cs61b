class Person {
 void speakTo(Person other) { System.out.println("kudos"); }
 void watch(SoccerPlayer other) { System.out.println("wow"); }
}

class Athlete extends Person {
 void speakTo(Athlete other) { System.out.println("take notes"); }
 void watch(Athlete other) { System.out.println("game on"); }
}

class SoccerPlayer extends Athlete {
 void speakTo(Athlete other) { System.out.println("respect"); }
 void speakTo(Person other) { System.out.println("hmph"); }
}

Person itai = new Person();
//SoccerPlayer shivani = new Person(); CE
Athlete sohum = new SoccerPlayer();
Person jack = new Athlete();
Athlete anjali = new Athlete();
SoccerPlayer chirasree = new SoccerPlayer();
itai.watch(chirasree); //"wow";
jack.watch(sohum); // "game on"
itai.speakTo(sohum); // "wow"
jack.speakTo(anjali); // "kudos"
anjali.speakTo(chirasree); // "take notes"
sohum.speakTo(itai);"hmph";
chirasree.speakTo((SoccerPlayer) sohum); "respect";
sohum.watch(itai);// "game on"
sohum.watch((Athlete) itai);//"game on"
        ((Athlete) jack).speakTo(anjali); "take notes"
        ((SoccerPlayer) jack).speakTo(chirasree); "respect"
        ((Person) chirasree).speakTo(itai); "hmph"


