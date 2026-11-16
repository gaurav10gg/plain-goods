# Plain Goods: a small JSP shop

Users browse products and buy them. Admins log in and add products.

| What | Where |
|---|---|
| Users, products, purchases | **SQL**: SQLite file `data/shop.db` |
| Admins | **NoSQL**: MongoDB, database `plaingoods`, collection `admins` |

## Run

1. Install MongoDB (or unzip it to `%USERPROFILE%\tools\mongodb`).
2. Double-click **`run.cmd`**. It starts MongoDB in a second window, then the site.
3. Open **http://localhost:8080**

Default admin: **admin / admin123** (link in the footer, or `/admin/login`).

You only need Java 17+. `mvnw.cmd` downloads Maven by itself the first time.

## Code

```
src/main/java/com/plaingoods/
  model/   User, Admin, Product, Purchase        plain classes
  db/      SqlDatabase (SQLite), NoSqlDatabase (MongoDB)
  dao/     UserDao, ProductDao, PurchaseDao (SQL), AdminDao (MongoDB)
  util/    PasswordUtil                          SHA-256 password hashing
  web/     one servlet per page + AppListener (creates tables, sample data, default admin)
src/main/webapp/
  WEB-INF/views/*.jsp                            the pages
  style.css
```

## Tables (SQLite)

```
users     (id, name, email UNIQUE, password)
products  (id, name, description, price)
purchases (id, user_id -> users, product_id -> products, price, purchased_at)
```

MongoDB `admins` document: `{ _id, username, password }`.

## See the data

One-time install of the two command-line tools (then open a new terminal):

```
winget install SQLite.SQLite
winget install MongoDB.Shell
```

**SQL (SQLite):** run from the project folder

```
sqlite3 -box data\shop.db ".tables"
sqlite3 -box data\shop.db "SELECT * FROM users;"
sqlite3 -box data\shop.db "SELECT * FROM products;"
sqlite3 -box data\shop.db "SELECT * FROM purchases;"
sqlite3 data\shop.db ".schema"
```

**NoSQL (MongoDB):** MongoDB must be running (`run.cmd` or `start-mongo.cmd`)

```
mongosh plaingoods --quiet --eval "show collections"
mongosh plaingoods --quiet --eval "db.admins.find()"
```

Prefer a GUI? *DB Browser for SQLite* opens `data\shop.db`; *MongoDB Compass* connects to `mongodb://127.0.0.1:27017`.
