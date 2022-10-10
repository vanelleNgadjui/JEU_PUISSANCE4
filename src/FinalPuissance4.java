import java.util.Scanner;

public class FinalPuissance4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int colonne = 7;
		int ligne = 6;
		
		int col=-1;
		//le gagnant
		int gagnant = 0;
		
		//Appel de fonction
		char[][] plateau=plateau(colonne,ligne);
		boucleDeJeu(plateau(colonne,ligne),colonne, ligne);
		VerifPlacement(plateau,colonne,ligne,col);
		placement(plateau,colonne,ligne,col);
		DetectionDeVictoire(plateau,colonne,ligne, col,gagnant);
	}
	public static char[][] plateau(int colonne, int ligne){
		//Tableau * pour les places vides x pour le joueur 1 et 0 pour joueur 2
		//colonne et ligne
		
		char[][] plateau = new char[colonne][ligne]; //Création du tableau
		
		
		
		//Remplissage des cases au debut par des '*' qui symbolise le vide
		for (int i=0; i<colonne; i++) 
			for (int j =0; j<ligne; j++)
				plateau[i][j]= '*';
		return plateau;
	}
	
	
	public static void boucleDeJeu(char[][]tab, int colonne,int ligne) {
		//Nombre de tours 
		for (int tour=1; tour<= colonne*ligne;tour++) {
			
			//Affiche du tableau
			System.out.println("Tour n°"+ tour +", état du plateau:");
			for (int boucle = 0; boucle < colonne+2+2*colonne; boucle++)System.out.print('-'); 
			//colonne+2+2*colonne c'est pour mettre deux trait par colonne
			System.out.println();
			
			
			for(int y = 0; y<ligne;y++) {
				System.out.print('|');
				for (int x=0; x<colonne;x++) {
					System.out.print(" "+ tab[x][y]+ " ");
				}
				System.out.print('|');
				System.out.println();
			}
			
			for (int boucle = 0; boucle < colonne+2+2*colonne; boucle++)System.out.print('-');
			System.out.println();
		}
	}
	
	public static void VerifPlacement(char[][]tab, int colonne,int ligne, int col) {
		
		for (int tour=1; tour<= colonne*ligne;tour++) {
			//Placement de jeton
			System.out.println("Tour du joueur "+ (tour%2==1 ? 'X'/*1*/:'O'/*2*/)); //methode ternaire
			System.out.println("Entrez le numéro de la colonne entre 1 et  "+ colonne );
			
			boolean placement = false; //le jetton n'est pas encore placé
			
			
			while (!placement) {
				col=-1;
				Scanner scanner = new Scanner(System.in);
				String lig = scanner.nextLine();
				
				
				//Verification que la ligne est un entier entre 1 et Colonne
				try {
					col= Integer.valueOf(lig);// transformation en entier
					if (col>= 1 && col<=colonne){ //entier compris entre 1 et le nombre de colonne
						if(tab[col-1][0] != '*') { System.out.println("colonne pleine ."
								+ " Rejouez!");
						} //si le nombre entre est bon on verifie 
						//si la colonne est pleine
						else {
							placement =true; //on sort de la boucle while
						}
					}
					else {System.out.println("nombre incorrect. Rejouez!");}
				}catch(Exception e){System.out.println("nombre incorrect recommencez!");}//Si c'est pas 
				//un entier réiterer
			}
		}
	}
	
	public static void placement(char[][]tab, int colonne, int ligne,int col) {
		
		for (int tour=1; tour<= colonne*ligne;tour++) {
			//Placement de jetton
			int rang = ligne-1;
			while(tab[col-1][rang] != '*') {
				rang--; // cherchre l'emplacement vide
			}
			tab[col-1][rang] =  (tour%2==1 ? 'X'/*1*/:'O'/*2*/);//placement du jeton a l'emplacement vide 
			//trouver par la boucle while
		}
	}
	
	public static void DetectionDeVictoire(char[][]tab, int colonne, int ligne, int col,int gagnant) {
		
		for (int tour=1; tour<= colonne*ligne;tour++) {
			//Detection de la victoire
			/*victoire lorsque l'on a placé quatre jeton alignés
			 * elle est déclenché juste apres le placement de jetton*/
			char symbole =(tour%2==1 ? 'X'/*1*/:'O'/*2*/);
			/*la variable symbole pour se rappeler le symbole du joueur*/
			int max = 0; //detecter l'alignement maximale trouvé lors du placement de victoire. 
			//LOrsqu'il est arrivé a 4 il y a victoire
			int x; int y;
			int somme;
			int rang= ligne-1;
			//Verification de l'alignement sur la diagonale hautGauche- basDroite
			x=col-1; 
			y=rang;
			somme=-1;
			//SOMME EGALE MOINS 1 CAR ON COMPTE LE JETTON QU4ON A PLAC2 DEUX FOIS
			while(y>=0 && x >=0 && tab[x][y]==symbole) {
				y--;
				x--;
				somme++;
			}
			x=col-1; 
			y=rang;
			while(y< ligne && x<colonne  && tab[x][y]==symbole) {
				y++;
				x++;
				somme++;
			}
			if(somme>max)max=somme;
			
			//Verification diagonale hautDroite- basGauche
			x=col-1; 
			y=rang;
			somme=-1;
			while(y>=0 && x >=0 && tab[x][y]==symbole) {
				y--;
				x++;
				somme++;
			}
			x=col-1; 
			y=rang;
			while(y<ligne && x<colonne  && tab[x][y]==symbole) {
				y++;
				x--;
				somme++;
			}
			if(somme>max)max=somme;
			
			//Verification sur la verticale
			x=col-1; 
			y=rang;
			somme=-1;
			while(y>=0 && tab[x][y]==symbole) {
				y--;
				somme++;
			}
			y=rang;
			while(y<ligne && tab[x][y]==symbole) {
				y++;
				somme++;
			}
			if(somme>max)max=somme;
			
			//Verification sur l'horizontale
			x=col-1; 
			y=rang;
			somme=-1;
			while(x >=0 && tab[x][y]==symbole) {
				x--;
				somme++;
			}
			x=col-1; 
			while(x<colonne  && tab[x][y]==symbole) {
				x++;
				somme++;
			}
			if(somme>max)max=somme;
			
	
		//Alignement qui sera utilisé pour déterminer le gagnant
		int puissance4=4;
		
		//verification si la variable max est egale a 4
			if (max>=puissance4) {
				gagnant=(tour%2==1 ? 1:2);
				tour=colonne*ligne+1; //POUR SORTIR DE LA BOUCLE FOR
			}
		
		
		System.out.println (".........................");
		}
		//Affichage du réesultat
				System.out.println ();
				System.out.println ("..........................");
				System.out.println ("......FIN DE PARTIE.......");
				System.out.println ("..........................");
				
				if(gagnant ==0)
					System.out.println ("........EGALITE.......");
				if (gagnant == 1)
					System.out.println (".......VICTOIRE DE X......");
				if (gagnant == 2)
					System.out.println (".......VICTOIRE DE O......");
				System.out.println ("........................");
				System.out.println (".....ETAT DU PLATEAU......");
				System.out.println (".........................");
				
				//Affiche du tableau
				for (int boucle = 0; boucle < colonne+2+2*colonne; boucle++)System.out.print('-');
				System.out.println();
				
				
				for(int y = 0; y<ligne;y++) {
					System.out.print('|');
					for (int x=0; x<colonne;x++) {
						System.out.print(" "+ tab[x][y]+ " ");
					}
					System.out.print('|');
					System.out.println();
				}
				
				for (int boucle = 0; boucle < colonne+2+2*colonne; boucle++)System.out.print('-');
				System.out.println();
				
				//Fin du programme
			}
	}


