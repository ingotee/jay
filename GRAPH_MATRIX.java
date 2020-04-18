
public class GRAPH_MATRIX{    
    private int AnzahlKnoten = 0;   // Counter für aktuelle Knotenanzahl
    private KNOTEN[] Knoten;        // speichert alle Knoten 
    private int [][] Matrix;        // speichert alle Kanten 
    boolean [] Besucht;             // speichert ab, welche Knoten besucht wurden
    
    public GRAPH_MATRIX(int KnotenMax){                
        Knoten = new KNOTEN[KnotenMax];
        Matrix = new int [KnotenMax][KnotenMax];
        Besucht = new boolean [KnotenMax]; 

    }
   
    public void MatrixAusgeben(){
        // Gibt den Tabellenkopf aus
        System.out.print("    ");
        
        for(int i = 0; i < AnzahlKnoten; i++){ 
            System.out.printf("%5s", Knoten[i].BezeichnungGeben());
        } 
        System.out.println();
        // Gibt die Zeilen der Tabelle aus
        for(int z = 0; z < AnzahlKnoten; z++){ 
            System.out.print(Knoten[z].BezeichnungGeben() + " ");
            for(int s = 0; s < AnzahlKnoten; s++){
                System.out.printf("%5d", Matrix[z][s]);
            }
            System.out.println();
        } 
        
    }
    
    public void ArraysVergroeßern(){
        KNOTEN[] tmpKnoten;     // temporäres Array aller Knoten
        int [][] tmpMatrix;     // temporäre Matrix aller Kanten
        boolean [] tmpBesucht;  // temporäres BesuchtArray 
        
        // temporären Variablen ihr Objekt zuweisen
        tmpKnoten = new KNOTEN[Knoten.length + 1]; 
        tmpMatrix = new int[Knoten.length + 1][Knoten.length + 1];
        tmpBesucht = new boolean [Knoten.length + 1];            
        
        // Werte der Ursprungsarrays in die temporären Arrays übertragen
        System.arraycopy(Knoten,0,tmpKnoten,0,AnzahlKnoten);
        System.arraycopy(Besucht,0,tmpBesucht,0,AnzahlKnoten);
        
        // Werte der Ursprungsmatrix zeilenweise in die temporäre Matrix übertragen
        for (int i = 0; i < AnzahlKnoten; i++){
            System.arraycopy(Matrix[i],0,tmpMatrix[i],0,AnzahlKnoten);
        }
         
        // temporären Arrays der jeweiligen Variable zuweisen
        Knoten = tmpKnoten;
        Besucht = tmpBesucht;
        Matrix = tmpMatrix;
                      
    }
    
    public void KanteAendern (String Beginn, String Ende, int GewNeu){ 
        // Kante darf nicht geändert werden, wenn Beginn == Ende 
        if(Beginn != Ende){
            // Nummern der betreffenden Kanten suchen
            int Beg = KnotenNummer(Beginn);
            int End = KnotenNummer(Ende);
            
            // Wenn beide Knoten existieren, Kante ändern
            if((Beg != -1) && (End != -1)){
                Matrix [Beg][End] = GewNeu;
                Matrix [End][Beg] = GewNeu;
            }        
        }
    }
    
    public void KanteLoeschen(String Beginn, String Ende){                
        KanteAendern(Beginn, Ende, -1);
    }
    
    public void KnotenEinfuegen(String Bezeichnung){
        // Prüft ob Array voll ist
        if(AnzahlKnoten == Knoten.length){
            // Wenn Array voll, Array erweitern
            ArraysVergroeßern();
        }
        
        // Neuen Knoten an nächste freie Stelle hinzufügen
        Knoten[AnzahlKnoten] = new KNOTEN (Bezeichnung);
        //beachte: erster Feldindex ist 0;
        
        // Abstand zu sich selbst eintragen 
        Matrix [AnzahlKnoten][AnzahlKnoten] = 0;
                    
        // in der Matrix vermerken, dass ein neuer Knoten eingefügt wurde 
        // (der allerdings noch nicht erreichbar ist)            
        for (int i = 0; i < AnzahlKnoten; i = i+1){
                Matrix [AnzahlKnoten][i] = -1;
                Matrix [i][AnzahlKnoten] = -1;
        }
        
        // Anzahl der Knoten korrigieren
        AnzahlKnoten++;        
    }
                      
    public String KnotenEntfernen(String Kno){ 
        // entsprechende Knotennummer holen
        int KnoNum = KnotenNummer(Kno); 
        // Wenn Knoten existiert, dann Knoten aus Knotenarray entfernen und Matrix anpassen
        if(KnoNum != -1){            
            for(int i = KnoNum; i < AnzahlKnoten - 1; i++){
                // Knoten mit dem nächsten Knoten überschreiben
                Knoten[i] = Knoten[i + 1];
                // blauer Teil der Matrix               
                for(int j = 0; j < KnoNum; j++){
                    // Matrixzelle mit der eins tieferen Zelle überschreiben
                    Matrix[i][j] = Matrix[i + 1][j];
                    // Matrixzelle mit der eins weiter rechten Zelle überschreiben
                    Matrix[j][i] = Matrix[j][i + 1]; 
                }                
            }
            
            for(int i = KnoNum; i < AnzahlKnoten - 1; i++){
                //rosa Teil der Matrix
                for(int j = KnoNum; j < AnzahlKnoten - 1; j++){
                    // Matrixzelle mit der eins weiter rechten und eins tieferen Zelle überschreiben
                    Matrix[i][j] = Matrix[i + 1][j + 1];                    
                }                  
            }
            
            // Kanten der letzten Zeile und Spalte löschen
            for(int i = 0; i < AnzahlKnoten; i++){ 
                Matrix[i][AnzahlKnoten - 1] = -1;
                Matrix[AnzahlKnoten - 1][i] = -1;
            }
            // Letzten Knoten aus dem Knotenarray löschen
            Knoten[AnzahlKnoten - 1] = null;
            AnzahlKnoten--;
            return Kno;
        }else{
            return "Knoten nicht gefunden";
        }               
    }
    
    public int KnotenNummer(String Bezeichnung){
        //sucht die Nummer des Knoten mit der gewünschten Bezeichnung
        //(-1, falls nicht vorhanden)
        
        int Nr = -1;
        //Wert von Nr wird am Ende zurückgegeben
        
        for (int i = 0; i < AnzahlKnoten; i = i+1){
            if(Knoten[i].BezeichnungGeben().equals(Bezeichnung)){
                Nr = i;
                // Abbruch der Schleife, da Knoten gefunden wurde
                break; 
            }                           
        }
        return Nr;
    }
    
    public void KanteEinfuegen(String Von, String Nach, int Gewichtung){
        // Kante darf nicht eingefügt werden, wenn Von == Nach 
        if(Von != Nach){
            // Nummern der betroffenen Knoten suchen
            int VonNr = KnotenNummer(Von);
            int NachNr = KnotenNummer(Nach);
            
            // Wenns beide Knoten existieren, Eintrag hinzufügen
            if((VonNr != -1) && (NachNr != -1)){ 
                Matrix [VonNr][NachNr] = Gewichtung;
                Matrix [NachNr][VonNr] = Gewichtung;
            }        
        }
    }   
    
    // Ablauf aller Knoten genau einmal
    public void Tiefensuche (String Start){
        // Knotennummer des Startknotens holen
        int StartNr = KnotenNummer(Start);
        
        // Wenn Startknoten existiert, Rekursion starten
        if(StartNr != -1){
            // Vorbereitung: alle Knoten auf "nicht besucht" setzen
            for(int i = 0; i < Knoten.length; i++){
                Besucht[i] = false;
            }
            Besuchen(StartNr); // Start der Rekursion
        }        
    }
    
    public void Besuchen (int KnNr){
        // aktiven Knoten als "besucht" markieren
        Besucht[KnNr] = true;
        
        // Aktuellen Knoten ausgeben
        System.out.println(Knoten[KnNr].BezeichnungGeben() + ";");
        
        // In der Matrix nach benachbarten, noch nicht besuchten Knoten suchen
        for(int i = 0; i < Knoten.length; i++){
            if((Matrix[KnNr][i] > 0) && (Besucht[i] == false)){
                // Rekursion: nächster "freier" Knoten in der Liste wird besucht
                Besuchen(i); 
            }
        }
        System.out.println(Knoten[KnNr].BezeichnungGeben() + "(fertig)");
    }
    
    // Sucht alle Wege zwischen zwei Punkten 
    public void WegeSuchen (String StartKnoten, String ZielKnoten){
        // Wenn Startknoten == Zielknoten, soll kein Weg gesucht werden
        if(StartKnoten != ZielKnoten){
            // Jeweilige Knotennummern holen
            int StartNr = KnotenNummer(StartKnoten);
            int ZielNr = KnotenNummer(ZielKnoten);
            
            // Wenn beide Knoten existitieren, Rekursion starten
            if((StartNr != -1) && (ZielNr != -1)){
                // Vorbereitung: Alle Knoten auf nicht besucht setzen
                for(int i = 0; i < AnzahlKnoten; i++){
                    Besucht[i] = false;
                }
                // Rekursion starten
                Ablaufen(StartNr, ZielNr, StartKnoten, 0); 
            }
        }
    }
 
    public void Ablaufen(int KnotenNr, int ZielKnotenNr, String Pfad, int Laenge){
        int NeueLaenge;
        String NeuerPfad;
    
        // aktive Knoten auf besucht setzen
        Besucht[KnotenNr] = true;
        
        // Wenn der Zielknoten erreicht wird, dann abbrechen und Pfad ausgeben
        if(KnotenNr == ZielKnotenNr){
            System.out.println(Pfad + "; Länge " + Laenge);                    
        }else{  // sonst alle existierenden und noch möglichen Abzweigungen ablaufen

            // in der Matrix die Zeile des aktiven Knotens nach Kanten durchforsten
            for(int AbzweigNr = 0; AbzweigNr < AnzahlKnoten; AbzweigNr++){
                // Wenn es eine Kante gibt, deren Zielknoten noch nicht besucht sind
                if((Matrix[KnotenNr][AbzweigNr] > 0) && (!(Besucht[AbzweigNr]))){
                    // Weg in Richtung der Abzweigung verlängern
                    NeuerPfad = Pfad + "/" + Knoten[AbzweigNr].BezeichnungGeben();
                    NeueLaenge = Laenge + Matrix[KnotenNr][AbzweigNr];
                    // rekursiver Aufruf des Ablaufens
                    Ablaufen(AbzweigNr, ZielKnotenNr, NeuerPfad, NeueLaenge);
                }
            } 
        }
        //Knoten wieder freigeben
        Besucht[KnotenNr] = false;
    }   
}