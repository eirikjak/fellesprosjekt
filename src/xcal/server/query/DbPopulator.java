package xcal.server.query;


import java.util.Random;

public class DbPopulator {
	public static void main(String args[]){
		int supertall = 350;
		String rom = "Rom";
		int capacity;
		int id = 1;
		

		String[] fornavn = {"Knut", "Johnny", "Johanne", "Kari", "Kristine", "Katrine", "Sara", "Kristoffer", "Marius", "Ingeborg", "Aleksander", "Ingrid", "Anne", "Emma", "Liv","Eva", "Astrid",
                "Thea", "Jonas","Lucas","Kjell","Albert"};

		  String[] etternavn = {  "Hansen", "Olsen", "Stang", "Jensen", "Knutsen", "Aaberg", "Northug", "Damli" , "Jacobsen", "Thon", "Hagen" , "Boasson" , "Hushovd", "Johaug", "Sabeltan" , "Zuccarello" ,
                        "Gates", "Skrue", "Gjerdalen" , "Numme"};
		
		
		
	/*	for (int i = 0; i < 40; i++){
		       
			Random generator = new Random();
						
			int rollFornavn = generator.nextInt(21) + 1;
			int rollEtternavn = generator.nextInt(19) + 1;
			int rollTall = generator.nextInt(999) + 1;

			System.out.println("INSERT INTO Person(email, password, name, dateOfBirth) VALUES ( " + "'" + 
			(fornavn[rollFornavn] + "." + etternavn[rollEtternavn] + "."+ rollTall + "@xcal.com" )+"'" + 
			", " + "'xcal'" + ", "+"'"  + fornavn[rollFornavn] + etternavn[rollEtternavn] +"'" +", " + "1970-04-21);");
			
		}*/
		
		
		for (int i =0;i<50;i++){
			capacity = 5 +(int)(Math.random()*100);
		System.out.println("INSERT INTO Location(id) VALUES("+id+");");
		System.out.println("INSERT INTO Room(id, name, capacity) VALUES("+id+++", '"+rom+" X"+i+"', "+capacity+" );");
		}
	/*Person
	 * 	email-pk
	 * 	pw
	 * 	name
	 * 	dateOfBirth
	 * Room
	 * 	id
	 * 	name
	 * 	capacity
	 */
	}
	

}
