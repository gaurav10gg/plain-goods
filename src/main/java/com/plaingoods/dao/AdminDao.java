package com.plaingoods.dao;

import com.mongodb.client.model.Filters;
import com.plaingoods.db.NoSqlDatabase;
import com.plaingoods.model.Admin;
import com.plaingoods.util.PasswordUtil;
import org.bson.Document;

/** Admins live in MongoDB, collection "admins": { username, password } */
public class AdminDao {

    /** Returns the admin, or null if the username/password is wrong. */
    public Admin login(String username, String password) {
        Document doc = NoSqlDatabase.admins().find(Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", PasswordUtil.hash(password)))).first();
        return doc == null ? null : new Admin(doc.getString("username"));
    }

    /** Makes sure there is always one admin to log in with: admin / admin123 */
    public void createDefaultAdmin() {
        if (NoSqlDatabase.admins().countDocuments() == 0) {
            NoSqlDatabase.admins().insertOne(new Document("username", "admin")
                    .append("password", PasswordUtil.hash("admin123")));
        }
    }
}
