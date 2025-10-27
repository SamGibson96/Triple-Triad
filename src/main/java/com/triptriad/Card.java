package com.triptriad;

public class Card{
        private int id;
        private String name;
        private int level;
        private String type;
        private String element;
        private Stats stats;
        private boolean p1Posession;

        public Card() {}

        public Card(int id, String name, int level, String type, String element, Stats stats)
        {
            this.id = id;
            this.name = name;
            this.level = level;
            this.type = type;
            this.element = element;
            this.stats = stats;
            this.p1Posession = false;
        }

        public int getId() { return this.id; }
        public void setId(int id) { this.id = id; }

        public int getLevel() { return this.level; }
        public void setLevel(int level) { this.level = level; }

        public String getName() { return this.name; }
        public void setName(String name) { this.name = name; }

        public String getType() { return this.type; }
        public void setType(String type) { this.type = type; }

        public String getElement() { return this.element; }
        public void setElement(String element) { this.element = element; }

        public Stats getStats() { return stats; }
        public void setStats( Stats stats ) { this.stats = stats; }

        public boolean getPossesion() { return this.p1Posession; }
        public void setPossession(boolean p1Posession) { this.p1Posession = p1Posession; }

@Override
public String toString() {
    return "Card{" +
            "id=" + id +
            ", name = '" + name + '\'' +
            ", level = " + level +
            ", type = '" + type + '\'' +
            ", element = '" + element + '\'' +
            ", stats = " + stats +
            '}';
}

/*
 * Method used to print representation of a Card object to the screen, this method is not currently used but is
 * planned for used in a later planned feature.
 */
public void printCard(){
        String topEdge = " -------------------";
        String mid = "|                   |";
        String topNum = "|         " + this.stats.getTop() + "         |";
        String botNum = "|         " + this.stats.getBottom() + "         |";
        
        
            System.out.println(topEdge);
            System.out.println(topNum);
        for(int i = 1; i<=14; i++)
            {
                if(i == 7)
                {   
                    int space = (17 - this.name.length())/2;
                    System.out.print( "|" + stats.getLeft());
                    for(int j = 0; j < space; j ++) { System.out.print(" ");}
                    System.out.print(this.name);
                    for(int j = 0; j < space; j ++) { System.out.print(" ");}
                    System.out.println(this.stats.getRight() + "|");

                }
                else{
             System.out.println(mid);
                }
            }
        System.out.println(botNum);
        System.out.println(topEdge);
        }   

}
