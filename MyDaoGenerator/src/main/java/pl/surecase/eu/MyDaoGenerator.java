package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1009, "com.kai.gwtwohot");
        addFavoriteItem(schema);
        addAPIKey(schema);
        addEvent(schema);
        addNotificationItems(schema);
        addDungeon(schema);

        new DaoGenerator().generateAll(schema, "app/src/main/java-gen");
    }

    public static void addFavoriteItem(Schema schema) {
        Entity favoriteItem = schema.addEntity("FavoriteItem");
        favoriteItem.addIdProperty().autoincrement();
        favoriteItem.addIntProperty("gw_item_id").notNull();
    }

    public static void addAPIKey(Schema schema) {
        Entity ApiKey = schema.addEntity("APIKey");
        ApiKey.addIdProperty().autoincrement();
        ApiKey.addStringProperty("permissions").notNull();
        ApiKey.addStringProperty("name").notNull();
        ApiKey.addStringProperty("apiId").notNull();
        ApiKey.addStringProperty("key").notNull();
        ApiKey.addBooleanProperty("isSelected").notNull();
    }

    public static void addDungeon(Schema schema) {
        Entity dungeon = schema.addEntity("Dungeon");
        dungeon.addIdProperty().autoincrement();
        dungeon.addStringProperty("dungeon").notNull();
        dungeon.addStringProperty("path").notNull();
        dungeon.addStringProperty("goldreward").notNull();
        dungeon.addStringProperty("tokenreward").notNull();
        dungeon.addBooleanProperty("isSelected").notNull();
        dungeon.addDateProperty("dateAdded").notNull();
    }

    public static void addEvent(Schema schema) {
        Entity ApiKey = schema.addEntity("Event");
        ApiKey.addIdProperty().autoincrement();
        ApiKey.addStringProperty("area").notNull();
        ApiKey.addStringProperty("boss").notNull();
        ApiKey.addIntProperty("spawntime_utc_minutes").notNull();
        ApiKey.addStringProperty("zone").notNull();
    }

    public static void addNotificationItems(Schema schema) {
        Entity ApiKey = schema.addEntity("NotificationItem");
        ApiKey.addIdProperty().autoincrement();
        ApiKey.addIntProperty("gw_item_id").notNull();
        ApiKey.addStringProperty("name").notNull();
        ApiKey.addStringProperty("icon").notNull();
        ApiKey.addIntProperty("buyPrice");
        ApiKey.addIntProperty("sellPrice");
        ApiKey.addBooleanProperty("isGreater");
        ApiKey.addBooleanProperty("isBuy");
        ApiKey.addBooleanProperty("isNotified").notNull();
    }

}
