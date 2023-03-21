    public class MarshallCard {

        private static MarshallCard [] theCards = new MarshallCard[52];

        public enum Rank {
            ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

            public int count(){
                return Math.min(ordinal()+1, 10);
            }

            public String toShortString(){
                if(ordinal()==0)
                    return "A";
                else if(ordinal() < 10)
                    return String.valueOf(ordinal() + 1);
                else if(ordinal() == 10)
                    return "J";
                else if(ordinal() == 11)
                    return "Q";
                else if(ordinal() == 12)
                    return "K";
                else return "ERROR";
            }
        }

        public enum Suit {
            SPADES, DIAMONDS, CLUBS, HEARTS;

            public String toShortString(){
                if(ordinal()==0)
                    return "S";
                if(ordinal()==1)
                    return "D";
                if(ordinal()==2)
                    return "C";
                if(ordinal()==3)
                    return "H";
                else
                    return "ERROR";
            }
        }

        private int cardID;

        private MarshallCard(Rank r, Suit s){
            switch (r){
                case ACE:   ;
                case TWO:   ;
                case THREE: ;
                case FOUR:  ;
                case FIVE:  ;
                case SIX:   ;
                case SEVEN: ;
                case EIGHT: ;
                case NINE:  ;
                    cardID = r.count();
                    break;
                case TEN:
                    cardID = 9;
                case JACK:
                    cardID = 10;
                    break;
                case QUEEN:
                    cardID = 11;
                    break;
                case KING:
                    cardID = 12;
            }

            switch(s){
                case CLUBS:
                    break;
                case DIAMONDS:
                    cardID = cardID + 13;
                    break;
                case HEARTS:
                    cardID = cardID + 26;
                    break;
                case SPADES:
                    cardID = cardID + 39;
            }
        }

        private MarshallCard(int i){
            cardID = i;
        }

        public Rank getRank(){
            switch(cardID%13){
                case 0: return Rank.ACE;
                case 1: return Rank.TWO;
                case 2: return Rank.THREE;
                case 3: return Rank.FOUR;
                case 4: return Rank.FIVE;
                case 5: return Rank.SIX;
                case 6: return Rank.SEVEN;
                case 7: return Rank.EIGHT;
                case 8: return Rank.NINE;
                case 9: return Rank.TEN;
                case 10: return Rank.JACK;
                case 11: return Rank.QUEEN;
                case 12: return Rank.KING;
            }
            return Rank.ACE;
        }

        public Suit getSuit(){
            switch (cardID/13){
                case 0: return Suit.CLUBS;
                case 1: return Suit.DIAMONDS;
                case 2: return Suit.HEARTS;
                case 3: return Suit.SPADES;
            }
            return Suit.SPADES;
        }

        public int getCribCount(){
            return this.getRank().count();
        }

        @Override
        public String toString(){
            String rank = "", suit = "";

            if(cardID ==1){
                rank = "A";
            }else if(cardID%13<9){
                rank = Integer.toString(this.getRank().count());
            }else if(cardID%13 == 9){
                rank = "T";
            }else if(cardID%13 == 10){
                rank = "J";
            }else if(cardID%13 == 11){
                rank = "Q";
            }else
                rank = "K";

            switch(cardID/13){
                case 0:
                    suit = "\u2663";
                    break;
                case 1:
                    suit = "\u2662";
                    break;
                case 2:
                    suit = "\u2661";
                    break;
                case 3:
                    suit = "\u2660";
            }

            return rank+suit;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof MarshallCard)
                return equals((MarshallCard)o);
            else
                return false;
        }

        public boolean equals(MarshallCard c){
            return(this.cardID == c.cardID);
        }

        public static MarshallCard getCard(Rank r, Suit s){
            int cardNumber = 0;

            switch (r){
                case ACE:   ;
                case TWO:   ;
                case THREE: ;
                case FOUR:  ;
                case FIVE:  ;
                case SIX:   ;
                case SEVEN: ;
                case EIGHT: ;
                case NINE:  ;
                    cardNumber = r.count();
                    break;
                case TEN:
                    cardNumber = 9;
                    break;
                case JACK:
                    cardNumber = 10;
                    break;
                case QUEEN:
                    cardNumber = 11;
                    break;
                case KING:
                    cardNumber = 12;
            }

            switch(s){
                case CLUBS:
                    break;
                case DIAMONDS:
                    cardNumber = cardNumber + 13;
                    break;
                case HEARTS:
                    cardNumber = cardNumber + 26;
                    break;
                case SPADES:
                    cardNumber = cardNumber + 39;
            }
            if(theCards[cardNumber] == null) {
                MarshallCard newCard = new MarshallCard(r, s);
            }
            return new MarshallCard(cardNumber);
        }

        public static MarshallCard getCard(int i){
            if (theCards[i] == null){
                theCards[i] = new MarshallCard(i);
            }
            return new MarshallCard(i);
        }
    }
