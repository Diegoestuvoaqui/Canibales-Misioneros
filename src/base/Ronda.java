package base;

public class Ronda {
    //Estructura
    short[] derecha;
    short[] izquierda;
    short[] barco;


    public Ronda(){
        this.derecha= new short[6];
        this.izquierda= new short[6];
        this.barco=new short[2];

    }

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

    public int distancia(){
        int d=0;
        short[] derecha= getDerecha();
        for(short o:derecha){
            if(o==1) d++;
            if(o==2) d++;
        }
        return d;
    }


}