package net.berndreiss;

public enum Street {
    PRE_FLOP, FLOP, TURN, RIVER, SHOWDOWN;

    public Street getNext(){
        return Street.values()[this.ordinal()+1];
    }
}