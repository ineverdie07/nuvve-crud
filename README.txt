Hi

Before execute the docker image of this springboot project, first should run the Postgressql DB
container:

docker run --name postgresql -e POSTGRES_USER=postgresdbroot -e POSTGRES_PASSWORD=pas123 -p 5432:5432 -v /data:/var/lib/postgresql/data -d postgres

Then inside of that container should create nuvvedb Database.

