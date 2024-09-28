# Player Registration System Rest API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)  ![H2 Database](https://img.shields.io/badge/H2%20Database-018bff?style=for-the-badge&logoColor=white) 

I developed a Rest API for a player registration system. This Rest API was built by using **Spring Boot and Java**. This system allows registering players with their name, email, and phone number (not mandatory), and also automatically assigns them a codename retrieved from two external files available in JSON (vingadores.json) and XML (liga_da_justica.xml) formats, respectively. Each external file contains a list of codenames, and a single codename can only be assigned to one player. 

This project was inspired by the assessment test available at the following link: https://github.com/uolhost/test-backEnd-Java


## Requirements

Below are some business rules that were important for the functioning:

• Allow the registration of players according to the codenames contained in the reference links vingadores.json and liga_da_justica.xml.

• The name and email are mandatory, phone number is optional.

• Persist the registered information in an in-memory database.

• Retrieve, at any time, the list of all registered players with their respective codenames, along with the information of which list the codename was extracted from.

• Prevent the use of the same codename for different users (unless the codename is from different lists).

• Include the chosen codename within the lists vingadores.json or liga_da_justica.xml.

• It is mandatory to read the codename information from the files on the internet (links below). Attention: It is not allowed to store the codename information locally (in a file, a class, a database, etc.).

• The player registers their name, email, and phone number, and chooses the desired list (vingadores.json or liga_da_justica.xml).

• The system must check whether the chosen list has available codenames or not. If it does, one of them is selected and assigned to the player. Otherwise, inform that there are no more available codenames in that list.

## Links to external files (vingadores.json and liga_da_justica.xml)

https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json

https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/liga_da_justica.xml

The external files contain the information below:

vingadores.json
```
{
	"vingadores": [{
			"codinome": "Hulk"
		},
		{
			"codinome": "Capitão América"
		},
		{
			"codinome": "Pantera Negra"
		},
		{
			"codinome": "Homem de Ferro"
		},
		{
			"codinome": "Thor"
		},
		{
			"codinome": "Feiticeira Escarlate"
		},
		{
			"codinome": "Visão"
		}
	]
}
```
liga_da_justica.xml
```
<liga_da_justica>
	<codinomes>
			<codinome>Lanterna Verde</codinome>
			<codinome>Flash</codinome>
			<codinome>Aquaman</codinome>
			<codinome>Batman</codinome>			
			<codinome>Superman</codinome>			
			<codinome>Mulher Maravilha</codinome>			
	</codinomes>
</liga_da_justica>
```

## Database Config
For this API, an embedded Database (H2 Database) was used with the following configuration properties:

- Name: player_registration_system_db
- Username: sa
- Password:

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.3.4
- Java version: 17

## System Class Diagram

![PlayerRegistrationSystemClassDiagram](https://github.com/user-attachments/assets/d653cb13-7745-4dbd-b6a1-eb670a3591a8)



