# PostgreSQL Docker Setup

## 🚀 Run PostgreSQL Container

```bash
docker run -d --name expense-database -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=expense_tracker_app -p 5432:5432 -v pgdata:/var/lib/postgresql/data postgres
```

## 🛠 Interact with the Database

```bash
docker exec -it expense-database psql -U postgres -d expense_tracker_app