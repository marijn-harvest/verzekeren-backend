verzekeren-backend

Om een postgres-database voor deze applicatie op te zetten kan het volgende commando uitgevoerd worden:
docker run --name verzekeren -e POSTGRES_PASSWORD=verzekeren -e POSTGRES_USER=verzekeren -d postgres -p 5432:5432