package base;

public class Ronda {
    //Estructura
    short[] derecha;
    short[] izquierda;
    short[] barco;
    /*private static short contador;//static hace que compartan todas las clase ronda la misma variable
    //private short id;*/

    public Ronda(){
        //contador++;
        //this.id=contador;
        this.derecha= new short[6];
        this.izquierda= new short[6];
        this.barco=new short[2];

    }
    /*public short getId() {
        return id;
    }
    public void setId(short id) {
        this.id = id;
    }*/
    public short[] getDerecha() {
        return derecha;
    }
    public void setDerecha(short[] derecha) {
        this.derecha = derecha;
    }
    public short[] getIzquierda() {
        return izquierda;
    }
    public void setIzquierda(short[] izquierda) {
        this.izquierda = izquierda;
    }
    public short[] getBarco() {
        return barco;
    }
    public void setBarco(short[] barco) {
        this.barco = barco;
    }


    //vacio=0,canibal=1,misionero=2
    public boolean estadoMeta(){
        boolean moneda = false;
        if(getDerecha()!=null){
            int c=0,m=0;
            for(short i:getDerecha()){
                if(i==1) c++;
                if(i==2) m++;
            }
            if(c==3&&m==3) {
                moneda = true;
            }
        }else throw new NullPointerException();
        return moneda;
    }
    public void estadoInicial(){
        short[] derecha = getDerecha();
        short[] izquierda = getIzquierda();
        if(derecha!=null&&izquierda!=null){
        for(int i=0;i<6;i++) {
            derecha[i] = 0;
        }
        for(int j=0;j<6;j++){
            if(j<(izquierda.length/2)){
                izquierda[j]=1;
            }else izquierda[j]=2;
        }
        }
    }
    public short distancia(){
        short d=0;
        short[] derecha= getDerecha();
        for(short o:derecha){
            if(o==1) d++;
            if(o==2) d++;
        }
        return d;
    }
    public short riego(){
        short riesgo=0;
        short c1=0;
        short m1=0;
        short c2=0;
        short m2=0;
        for (int i=0;i<6;i++){
            if(derecha[i]==1) c1++;
            else if (derecha[i]==2) m1++;
            if(izquierda[i]==1) c2++;
            else if (izquierda[i]==2) m2++;
        }
        if(m1>0&&c1>m1) riesgo++;
        if(m2>0&&c2>m2) riesgo++;

        return riesgo;
    }


}