import java.util.Scanner;

public class methodes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//colonnes et lignes :
		int colonne = 8;
		int ligne = 7;
		
		char[][]plateau=plateauDeJeu(ligne, colonne);
		boucleDeJeu(plateau, ligne, colonne);
		int gagnant =Jeu(plateau, ligne, colonne);
		victoire(plateau, ligne, colonne, gagnant);
	}
	
	public static char[][] plateauDeJeu(int ligne, int colonne){
		//Tableau * pour les places vides x pour le joueur 1 et O pour joueur 2
		//'*' = emplacement vide  / 'X' = joueur 1  /  'O'  = joueur 2
		char[][] plateau = new char[colonne][ligne];  //Création du tableau
		//remplissage des cases avec du vide '*'
		for(int x = 0 ; x <colonne ; x++)
			for(int y = 0 ; y < ligne ; y++)
				plateau[x][y] = '*';
		return plateau;
	}
	
	
	public static void boucleDeJeu(char[][]plateau,int ligne,int colonne) {
		//boucle de jeu, s'arrete en cas de victoire de J1 ou J2 ou si le plateau est plein avec égalité
		//Nombre de tours 
		for(int tour = 1 ; tour <= colonne*ligne ; tour++){
			
			//affichage du plateau:
			System.out.println("Tour " + tour + ", Etat du plateau :");
			System.out.println();
			System.out.println("  1  2  3  4  5  6  7  8");
			
			for(int boucle = 0 ; boucle < colonne+2+2*colonne ; boucle++)System.out.print('-');
			//colonne+2+2*colonne c'est pour mettre deux trait par colonne
			
			System.out.println();
			
			for(int y = 0 ; y < ligne ; y++){
				System.out.print('|');
				for(int x = 0 ; x < colonne ; x++){
					System.out.print(" " + plateau[x][y] + " ");
				}
				System.out.print('|');
				System.out.println();
			}
			
			for(int boucle = 0 ; boucle < colonne+2+2*colonne ; boucle++)System.out.print('-');
			System.out.println();
			tour = colonne*ligne+1;//POUR SORTIR DE LA BOUCLE FOR
		}
	}
	
	
	public static int Jeu(char[][]plateau,int ligne,int colonne) {
		int puissance4= 5;
		int gagnant=0;
		for(int tour = 1 ; tour <= colonne*ligne ; tour++){
		//Placements du jeton:
		System.out.println("Tour du joueur " + (tour%2==1 ? 'X' : 'O') );
		System.out.println("Entrez le numéro de la colonne entre 1 et " + colonne + " ...");
		
		
		boolean placement = false;//le jetton n'est pas encore placé
		
		Scanner scanner = new Scanner(System.in);
		
		int col = -1;
		while(!placement){
			col = -1;
			String lig = scanner.nextLine();
			
			//Verification que la ligne est un entier entre 1 et Colonne
			try{
				col = Integer.valueOf(lig);// transformation en entier
				
				if(col >= 1 && col <= colonne){ //entier compris entre 1 et le nombre de colonne
					if(plateau[col - 1][0] != '*'){ //si le nombre entre est bon on verifie 
						//si la colonne est pleine
						System.out.println("Colonne pleine, réitérez");
					} else {
						placement = true;//on sort de la boucle while
					}
				} else {
					System.out.println("Nombre incorrect, réitérez");
				}
				
			}catch(Exception e){System.out.println("Nombre incorrect, réitérez");}//Si c'est pas 
			//un entier réiterer
			
		}
		//placement du jeton:
		int rang = ligne-1;
		while(plateau[col - 1][rang] != '*'){
			rang--; // cherchre l'emplacement vide
		}
		plateau[col - 1][rang] = (tour%2==1 ? 'X' : 'O');//placement du jeton a l'emplacement vide 
		//trouver par la boucle while
		
	
		//Détection de victoire:
		
		//symbole en cours:
		/*victoire lorsque l'on a placé quatre jeton alignés
		 * elle est déclenché juste apres le placement de jetton*/
		char symbole = (tour%2==1 ? 'X' : 'O');
		/*la variable symbole pour se rappeler le symbole du joueur*/
		
		//nombre alignés maximal:
		//detecter l'alignement maximale trouvé lors du placement de victoire. 
		//LOrsqu'il est arrivé a 4 il y a victoire
		int max = 0;
		int x; int y;
		int somme;
		
		//Verification sur la verticale
		//-->  verticale:
		x = col-1; y = rang; somme=-1;
		while(y >= 0 && plateau[x][y] == symbole){ y--; somme++;}
		y = rang;
		while(y < ligne && plateau[x][y] == symbole){ y++; somme++;}
		if(somme > max) max= somme;
		
		
		//Verification sur la horizontale
		//-->  horizontale:
		x = col-1; y = rang; somme=-1;
		while(x >= 0 && plateau[x][y] == symbole){ x--; somme++;}
		x = col-1;
		while(x < colonne && plateau[x][y] == symbole){ x++; somme++;}
		if(somme > max) max= somme;
		
		
		//Verification de l'alignement sur la diagonale hautGauche- basDroite
		//-->  diagonale HG-BD
		x = col-1; y = rang; somme=-1;
		//SOMME EGALE MOINS 1 CAR ON COMPTE LE JETTON QU4ON A PLAC2 DEUX FOIS
		while(y >= 0 && x >= 0 && plateau[x][y] == symbole){ y--; x--; somme++;}
		x = col-1; y = rang;
		while(y <ligne && x < colonne && plateau[x][y] == symbole){ y++; x++; somme++;}
		if(somme > max) max= somme;
		
		
		//Verification diagonale hautDroite- basGauche
		//-->  diagonale HD-BG
		x = col-1; y = rang; somme=-1;
		while(y >= 0 && x < colonne && plateau[x][y] == symbole){ y--; x++; somme++;}
		x = col-1; y = rang;
		while(y < ligne && x >= 0 && plateau[x][y] == symbole){ y++; x--; somme++;}
		if(somme > max) max= somme;
		
		
		
		//verification si la variable max est egale a 4
		if(max >= puissance4){
			gagnant = (tour%2==1 ? 1 : 2);
			tour = colonne*ligne+1;//POUR SORTIR DE LA BOUCLE FOR
		}
		System.out.println();
		System.out.println("---------------------------------");
		boucleDeJeu(plateau, ligne, colonne);
		}
		
		return gagnant;
		
}

		
	public static void victoire(char[][]plateau,int ligne,int colonne,int gagnant) {
		//affichage des résultats:
		// si gagnant == 0 c'est que tout le plateau s'est remplis sans gagnant, il y a donc  égalité
		System.out.println();
		System.out.println("---------------------------------");
		System.out.println("***********FIN DE PARTIE*********");
		System.out.println("---------------------------------");
		if(gagnant == 0)
			System.out.println("***********EGALITE*************");
		if(gagnant == 1)
			System.out.println("***********VICTOIRE DE X*********");
		if(gagnant == 2)
			System.out.println("***********VICTOIRE DE O**********");
		System.out.println("---------------------------------");
		
		
		//Affiche du tableau a la fin
		System.out.println();
		System.out.println("  1  2  3  4  5  6  7");
		for(int boucle = 0 ; boucle < colonne+2+2*colonne ; boucle++)System.out.print('-');
		System.out.println();
		
		for(int y = 0 ; y < ligne ; y++){
			System.out.print('|');
			for(int x = 0 ; x < colonne ; x++){
				System.out.print(" " + plateau[x][y] + " ");
			}
			System.out.print('|');
			System.out.println();
		}
		
		for(int boucle = 0 ; boucle < colonne+2+2*colonne ; boucle++)System.out.print('-');
		System.out.println();
		
		
		//Fin du programme
		
		
	}
	
}

