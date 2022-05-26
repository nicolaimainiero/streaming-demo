import { faker } from '@faker-js/faker';

let INSERT = `INSERT INTO author (first_name,last_name,year_of_birth) VALUES`


for (let i = 0; i< 100000; i++){
	let date = 1910 + parseInt(faker.random.numeric(2));
	let lastName = faker.name.lastName().replace("O'", "").replace("D'","");
	let firstName = faker.name.firstName().replace("O'", "").replace("D'","");
	INSERT = INSERT + ` ('${firstName}','${lastName}',${date}),\n`;
	
}

INSERT = INSERT + ';';

console.log(INSERT);
