package com.example.sublandlord.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.sublandlord.data.local.dao.LandlordContractDao;
import com.example.sublandlord.data.local.dao.LandlordContractDao_Impl;
import com.example.sublandlord.data.local.dao.LandlordDao;
import com.example.sublandlord.data.local.dao.LandlordDao_Impl;
import com.example.sublandlord.data.local.dao.LeaseDao;
import com.example.sublandlord.data.local.dao.LeaseDao_Impl;
import com.example.sublandlord.data.local.dao.PropertyDao;
import com.example.sublandlord.data.local.dao.PropertyDao_Impl;
import com.example.sublandlord.data.local.dao.ReminderDao;
import com.example.sublandlord.data.local.dao.ReminderDao_Impl;
import com.example.sublandlord.data.local.dao.RoomDao;
import com.example.sublandlord.data.local.dao.RoomDao_Impl;
import com.example.sublandlord.data.local.dao.TenantDao;
import com.example.sublandlord.data.local.dao.TenantDao_Impl;
import com.example.sublandlord.data.local.dao.TransactionDao;
import com.example.sublandlord.data.local.dao.TransactionDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class SubLandlordDatabase_Impl extends SubLandlordDatabase {
  private volatile PropertyDao _propertyDao;

  private volatile RoomDao _roomDao;

  private volatile LandlordDao _landlordDao;

  private volatile TenantDao _tenantDao;

  private volatile LandlordContractDao _landlordContractDao;

  private volatile LeaseDao _leaseDao;

  private volatile TransactionDao _transactionDao;

  private volatile ReminderDao _reminderDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `properties` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `address` TEXT NOT NULL, `city` TEXT NOT NULL, `district` TEXT NOT NULL, `latitude` REAL, `longitude` REAL, `type` TEXT NOT NULL, `area` REAL NOT NULL, `floor` TEXT NOT NULL, `totalRooms` INTEGER NOT NULL, `status` TEXT NOT NULL, `images` TEXT NOT NULL, `amenities` TEXT NOT NULL, `description` TEXT NOT NULL, `purchasePrice` REAL, `decorationCost` REAL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `rooms` (`id` TEXT NOT NULL, `propertyId` TEXT NOT NULL, `roomNumber` TEXT NOT NULL, `area` REAL NOT NULL, `floor` TEXT NOT NULL, `type` TEXT NOT NULL, `currentRentPrice` REAL NOT NULL, `baseRentPrice` REAL NOT NULL, `status` TEXT NOT NULL, `amenities` TEXT NOT NULL, `images` TEXT NOT NULL, `maxOccupants` INTEGER NOT NULL, `currentOccupants` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`propertyId`) REFERENCES `properties`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_rooms_propertyId` ON `rooms` (`propertyId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `landlords` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `phone` TEXT NOT NULL, `idCard` TEXT, `wechat` TEXT, `bankInfo` TEXT, `notes` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `tenants` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `phone` TEXT NOT NULL, `idCard` TEXT, `wechat` TEXT, `emergencyContact` TEXT, `roomId` TEXT, `status` TEXT NOT NULL, `creditScore` INTEGER, `documents` TEXT NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`roomId`) REFERENCES `rooms`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_tenants_roomId` ON `tenants` (`roomId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `landlord_contracts` (`id` TEXT NOT NULL, `propertyId` TEXT NOT NULL, `landlordId` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `monthlyRent` REAL NOT NULL, `deposit` REAL NOT NULL, `paymentFrequency` TEXT NOT NULL, `paymentDay` INTEGER NOT NULL, `paymentMethod` TEXT NOT NULL, `lateFee` TEXT, `status` TEXT NOT NULL, `contractUrl` TEXT, `signedDate` INTEGER, `renewalHistory` TEXT NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`propertyId`) REFERENCES `properties`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`landlordId`) REFERENCES `landlords`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_landlord_contracts_propertyId` ON `landlord_contracts` (`propertyId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_landlord_contracts_landlordId` ON `landlord_contracts` (`landlordId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `leases` (`id` TEXT NOT NULL, `roomId` TEXT NOT NULL, `tenantId` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `monthlyRent` REAL NOT NULL, `deposit` REAL NOT NULL, `paymentFrequency` TEXT NOT NULL, `paymentDay` INTEGER NOT NULL, `paymentMethod` TEXT NOT NULL, `utilities` TEXT NOT NULL, `lateFee` TEXT, `status` TEXT NOT NULL, `contractUrl` TEXT, `signedDate` INTEGER, `renewalHistory` TEXT NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`), FOREIGN KEY(`roomId`) REFERENCES `rooms`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`tenantId`) REFERENCES `tenants`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_leases_roomId` ON `leases` (`roomId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_leases_tenantId` ON `leases` (`tenantId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transactions` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `category` TEXT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `propertyId` TEXT, `roomId` TEXT, `contractId` TEXT, `relatedPartyId` TEXT, `relatedPartyName` TEXT, `status` TEXT NOT NULL, `paymentMethod` TEXT NOT NULL, `proofImages` TEXT NOT NULL, `description` TEXT NOT NULL, `tags` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reminders` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `targetDate` INTEGER NOT NULL, `advanceDays` INTEGER, `relatedEntityId` TEXT, `relatedEntityType` TEXT, `status` TEXT NOT NULL, `priority` TEXT NOT NULL, `sentDates` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'abfdfbcfa794c645498e8dfbe01248a9')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `properties`");
        db.execSQL("DROP TABLE IF EXISTS `rooms`");
        db.execSQL("DROP TABLE IF EXISTS `landlords`");
        db.execSQL("DROP TABLE IF EXISTS `tenants`");
        db.execSQL("DROP TABLE IF EXISTS `landlord_contracts`");
        db.execSQL("DROP TABLE IF EXISTS `leases`");
        db.execSQL("DROP TABLE IF EXISTS `transactions`");
        db.execSQL("DROP TABLE IF EXISTS `reminders`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsProperties = new HashMap<String, TableInfo.Column>(19);
        _columnsProperties.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("address", new TableInfo.Column("address", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("city", new TableInfo.Column("city", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("district", new TableInfo.Column("district", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("area", new TableInfo.Column("area", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("floor", new TableInfo.Column("floor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("totalRooms", new TableInfo.Column("totalRooms", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("images", new TableInfo.Column("images", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("amenities", new TableInfo.Column("amenities", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("purchasePrice", new TableInfo.Column("purchasePrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("decorationCost", new TableInfo.Column("decorationCost", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProperties.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProperties = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProperties = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProperties = new TableInfo("properties", _columnsProperties, _foreignKeysProperties, _indicesProperties);
        final TableInfo _existingProperties = TableInfo.read(db, "properties");
        if (!_infoProperties.equals(_existingProperties)) {
          return new RoomOpenHelper.ValidationResult(false, "properties(com.example.sublandlord.data.local.entity.PropertyEntity).\n"
                  + " Expected:\n" + _infoProperties + "\n"
                  + " Found:\n" + _existingProperties);
        }
        final HashMap<String, TableInfo.Column> _columnsRooms = new HashMap<String, TableInfo.Column>(15);
        _columnsRooms.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("propertyId", new TableInfo.Column("propertyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("roomNumber", new TableInfo.Column("roomNumber", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("area", new TableInfo.Column("area", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("floor", new TableInfo.Column("floor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("currentRentPrice", new TableInfo.Column("currentRentPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("baseRentPrice", new TableInfo.Column("baseRentPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("amenities", new TableInfo.Column("amenities", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("images", new TableInfo.Column("images", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("maxOccupants", new TableInfo.Column("maxOccupants", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("currentOccupants", new TableInfo.Column("currentOccupants", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsRooms.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRooms = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysRooms.add(new TableInfo.ForeignKey("properties", "CASCADE", "NO ACTION", Arrays.asList("propertyId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesRooms = new HashSet<TableInfo.Index>(1);
        _indicesRooms.add(new TableInfo.Index("index_rooms_propertyId", false, Arrays.asList("propertyId"), Arrays.asList("ASC")));
        final TableInfo _infoRooms = new TableInfo("rooms", _columnsRooms, _foreignKeysRooms, _indicesRooms);
        final TableInfo _existingRooms = TableInfo.read(db, "rooms");
        if (!_infoRooms.equals(_existingRooms)) {
          return new RoomOpenHelper.ValidationResult(false, "rooms(com.example.sublandlord.data.local.entity.RoomEntity).\n"
                  + " Expected:\n" + _infoRooms + "\n"
                  + " Found:\n" + _existingRooms);
        }
        final HashMap<String, TableInfo.Column> _columnsLandlords = new HashMap<String, TableInfo.Column>(9);
        _columnsLandlords.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("idCard", new TableInfo.Column("idCard", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("wechat", new TableInfo.Column("wechat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("bankInfo", new TableInfo.Column("bankInfo", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlords.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLandlords = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLandlords = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLandlords = new TableInfo("landlords", _columnsLandlords, _foreignKeysLandlords, _indicesLandlords);
        final TableInfo _existingLandlords = TableInfo.read(db, "landlords");
        if (!_infoLandlords.equals(_existingLandlords)) {
          return new RoomOpenHelper.ValidationResult(false, "landlords(com.example.sublandlord.data.local.entity.LandlordEntity).\n"
                  + " Expected:\n" + _infoLandlords + "\n"
                  + " Found:\n" + _existingLandlords);
        }
        final HashMap<String, TableInfo.Column> _columnsTenants = new HashMap<String, TableInfo.Column>(13);
        _columnsTenants.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("phone", new TableInfo.Column("phone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("idCard", new TableInfo.Column("idCard", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("wechat", new TableInfo.Column("wechat", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("emergencyContact", new TableInfo.Column("emergencyContact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("roomId", new TableInfo.Column("roomId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("creditScore", new TableInfo.Column("creditScore", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("documents", new TableInfo.Column("documents", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTenants.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTenants = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysTenants.add(new TableInfo.ForeignKey("rooms", "SET NULL", "NO ACTION", Arrays.asList("roomId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesTenants = new HashSet<TableInfo.Index>(1);
        _indicesTenants.add(new TableInfo.Index("index_tenants_roomId", false, Arrays.asList("roomId"), Arrays.asList("ASC")));
        final TableInfo _infoTenants = new TableInfo("tenants", _columnsTenants, _foreignKeysTenants, _indicesTenants);
        final TableInfo _existingTenants = TableInfo.read(db, "tenants");
        if (!_infoTenants.equals(_existingTenants)) {
          return new RoomOpenHelper.ValidationResult(false, "tenants(com.example.sublandlord.data.local.entity.TenantEntity).\n"
                  + " Expected:\n" + _infoTenants + "\n"
                  + " Found:\n" + _existingTenants);
        }
        final HashMap<String, TableInfo.Column> _columnsLandlordContracts = new HashMap<String, TableInfo.Column>(18);
        _columnsLandlordContracts.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("propertyId", new TableInfo.Column("propertyId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("landlordId", new TableInfo.Column("landlordId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("monthlyRent", new TableInfo.Column("monthlyRent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("deposit", new TableInfo.Column("deposit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("paymentFrequency", new TableInfo.Column("paymentFrequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("paymentDay", new TableInfo.Column("paymentDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("lateFee", new TableInfo.Column("lateFee", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("contractUrl", new TableInfo.Column("contractUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("signedDate", new TableInfo.Column("signedDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("renewalHistory", new TableInfo.Column("renewalHistory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLandlordContracts.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLandlordContracts = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysLandlordContracts.add(new TableInfo.ForeignKey("properties", "CASCADE", "NO ACTION", Arrays.asList("propertyId"), Arrays.asList("id")));
        _foreignKeysLandlordContracts.add(new TableInfo.ForeignKey("landlords", "CASCADE", "NO ACTION", Arrays.asList("landlordId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesLandlordContracts = new HashSet<TableInfo.Index>(2);
        _indicesLandlordContracts.add(new TableInfo.Index("index_landlord_contracts_propertyId", false, Arrays.asList("propertyId"), Arrays.asList("ASC")));
        _indicesLandlordContracts.add(new TableInfo.Index("index_landlord_contracts_landlordId", false, Arrays.asList("landlordId"), Arrays.asList("ASC")));
        final TableInfo _infoLandlordContracts = new TableInfo("landlord_contracts", _columnsLandlordContracts, _foreignKeysLandlordContracts, _indicesLandlordContracts);
        final TableInfo _existingLandlordContracts = TableInfo.read(db, "landlord_contracts");
        if (!_infoLandlordContracts.equals(_existingLandlordContracts)) {
          return new RoomOpenHelper.ValidationResult(false, "landlord_contracts(com.example.sublandlord.data.local.entity.LandlordContractEntity).\n"
                  + " Expected:\n" + _infoLandlordContracts + "\n"
                  + " Found:\n" + _existingLandlordContracts);
        }
        final HashMap<String, TableInfo.Column> _columnsLeases = new HashMap<String, TableInfo.Column>(19);
        _columnsLeases.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("roomId", new TableInfo.Column("roomId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("tenantId", new TableInfo.Column("tenantId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("monthlyRent", new TableInfo.Column("monthlyRent", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("deposit", new TableInfo.Column("deposit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("paymentFrequency", new TableInfo.Column("paymentFrequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("paymentDay", new TableInfo.Column("paymentDay", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("utilities", new TableInfo.Column("utilities", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("lateFee", new TableInfo.Column("lateFee", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("contractUrl", new TableInfo.Column("contractUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("signedDate", new TableInfo.Column("signedDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("renewalHistory", new TableInfo.Column("renewalHistory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeases.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLeases = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysLeases.add(new TableInfo.ForeignKey("rooms", "CASCADE", "NO ACTION", Arrays.asList("roomId"), Arrays.asList("id")));
        _foreignKeysLeases.add(new TableInfo.ForeignKey("tenants", "CASCADE", "NO ACTION", Arrays.asList("tenantId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesLeases = new HashSet<TableInfo.Index>(2);
        _indicesLeases.add(new TableInfo.Index("index_leases_roomId", false, Arrays.asList("roomId"), Arrays.asList("ASC")));
        _indicesLeases.add(new TableInfo.Index("index_leases_tenantId", false, Arrays.asList("tenantId"), Arrays.asList("ASC")));
        final TableInfo _infoLeases = new TableInfo("leases", _columnsLeases, _foreignKeysLeases, _indicesLeases);
        final TableInfo _existingLeases = TableInfo.read(db, "leases");
        if (!_infoLeases.equals(_existingLeases)) {
          return new RoomOpenHelper.ValidationResult(false, "leases(com.example.sublandlord.data.local.entity.LeaseEntity).\n"
                  + " Expected:\n" + _infoLeases + "\n"
                  + " Found:\n" + _existingLeases);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactions = new HashMap<String, TableInfo.Column>(17);
        _columnsTransactions.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("propertyId", new TableInfo.Column("propertyId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("roomId", new TableInfo.Column("roomId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("contractId", new TableInfo.Column("contractId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("relatedPartyId", new TableInfo.Column("relatedPartyId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("relatedPartyName", new TableInfo.Column("relatedPartyName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("proofImages", new TableInfo.Column("proofImages", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("tags", new TableInfo.Column("tags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactions.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactions = new TableInfo("transactions", _columnsTransactions, _foreignKeysTransactions, _indicesTransactions);
        final TableInfo _existingTransactions = TableInfo.read(db, "transactions");
        if (!_infoTransactions.equals(_existingTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "transactions(com.example.sublandlord.data.local.entity.TransactionEntity).\n"
                  + " Expected:\n" + _infoTransactions + "\n"
                  + " Found:\n" + _existingTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsReminders = new HashMap<String, TableInfo.Column>(13);
        _columnsReminders.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("targetDate", new TableInfo.Column("targetDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("advanceDays", new TableInfo.Column("advanceDays", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("relatedEntityId", new TableInfo.Column("relatedEntityId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("relatedEntityType", new TableInfo.Column("relatedEntityType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("sentDates", new TableInfo.Column("sentDates", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminders.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminders = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReminders = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReminders = new TableInfo("reminders", _columnsReminders, _foreignKeysReminders, _indicesReminders);
        final TableInfo _existingReminders = TableInfo.read(db, "reminders");
        if (!_infoReminders.equals(_existingReminders)) {
          return new RoomOpenHelper.ValidationResult(false, "reminders(com.example.sublandlord.data.local.entity.ReminderEntity).\n"
                  + " Expected:\n" + _infoReminders + "\n"
                  + " Found:\n" + _existingReminders);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "abfdfbcfa794c645498e8dfbe01248a9", "68015cda3e67ca24978d19c5ba0d070b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "properties","rooms","landlords","tenants","landlord_contracts","leases","transactions","reminders");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `properties`");
      _db.execSQL("DELETE FROM `rooms`");
      _db.execSQL("DELETE FROM `landlords`");
      _db.execSQL("DELETE FROM `tenants`");
      _db.execSQL("DELETE FROM `landlord_contracts`");
      _db.execSQL("DELETE FROM `leases`");
      _db.execSQL("DELETE FROM `transactions`");
      _db.execSQL("DELETE FROM `reminders`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PropertyDao.class, PropertyDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(RoomDao.class, RoomDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LandlordDao.class, LandlordDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TenantDao.class, TenantDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LandlordContractDao.class, LandlordContractDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LeaseDao.class, LeaseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionDao.class, TransactionDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReminderDao.class, ReminderDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PropertyDao propertyDao() {
    if (_propertyDao != null) {
      return _propertyDao;
    } else {
      synchronized(this) {
        if(_propertyDao == null) {
          _propertyDao = new PropertyDao_Impl(this);
        }
        return _propertyDao;
      }
    }
  }

  @Override
  public RoomDao roomDao() {
    if (_roomDao != null) {
      return _roomDao;
    } else {
      synchronized(this) {
        if(_roomDao == null) {
          _roomDao = new RoomDao_Impl(this);
        }
        return _roomDao;
      }
    }
  }

  @Override
  public LandlordDao landlordDao() {
    if (_landlordDao != null) {
      return _landlordDao;
    } else {
      synchronized(this) {
        if(_landlordDao == null) {
          _landlordDao = new LandlordDao_Impl(this);
        }
        return _landlordDao;
      }
    }
  }

  @Override
  public TenantDao tenantDao() {
    if (_tenantDao != null) {
      return _tenantDao;
    } else {
      synchronized(this) {
        if(_tenantDao == null) {
          _tenantDao = new TenantDao_Impl(this);
        }
        return _tenantDao;
      }
    }
  }

  @Override
  public LandlordContractDao landlordContractDao() {
    if (_landlordContractDao != null) {
      return _landlordContractDao;
    } else {
      synchronized(this) {
        if(_landlordContractDao == null) {
          _landlordContractDao = new LandlordContractDao_Impl(this);
        }
        return _landlordContractDao;
      }
    }
  }

  @Override
  public LeaseDao leaseDao() {
    if (_leaseDao != null) {
      return _leaseDao;
    } else {
      synchronized(this) {
        if(_leaseDao == null) {
          _leaseDao = new LeaseDao_Impl(this);
        }
        return _leaseDao;
      }
    }
  }

  @Override
  public TransactionDao transactionDao() {
    if (_transactionDao != null) {
      return _transactionDao;
    } else {
      synchronized(this) {
        if(_transactionDao == null) {
          _transactionDao = new TransactionDao_Impl(this);
        }
        return _transactionDao;
      }
    }
  }

  @Override
  public ReminderDao reminderDao() {
    if (_reminderDao != null) {
      return _reminderDao;
    } else {
      synchronized(this) {
        if(_reminderDao == null) {
          _reminderDao = new ReminderDao_Impl(this);
        }
        return _reminderDao;
      }
    }
  }
}
