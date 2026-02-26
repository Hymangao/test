package com.example.sublandlord.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.sublandlord.data.local.converter.Converters;
import com.example.sublandlord.data.local.entity.PropertyEntity;
import com.example.sublandlord.data.local.entity.PropertyStatus;
import com.example.sublandlord.data.local.entity.PropertyType;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Float;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PropertyDao_Impl implements PropertyDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PropertyEntity> __insertionAdapterOfPropertyEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<PropertyEntity> __deletionAdapterOfPropertyEntity;

  private final EntityDeletionOrUpdateAdapter<PropertyEntity> __updateAdapterOfPropertyEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeletePropertyById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllProperties;

  public PropertyDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPropertyEntity = new EntityInsertionAdapter<PropertyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `properties` (`id`,`name`,`address`,`city`,`district`,`latitude`,`longitude`,`type`,`area`,`floor`,`totalRooms`,`status`,`images`,`amenities`,`description`,`purchasePrice`,`decorationCost`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PropertyEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getAddress());
        statement.bindString(4, entity.getCity());
        statement.bindString(5, entity.getDistrict());
        if (entity.getLatitude() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getLongitude());
        }
        final String _tmp = __converters.fromPropertyType(entity.getType());
        statement.bindString(8, _tmp);
        statement.bindDouble(9, entity.getArea());
        statement.bindString(10, entity.getFloor());
        statement.bindLong(11, entity.getTotalRooms());
        final String _tmp_1 = __converters.fromPropertyStatus(entity.getStatus());
        statement.bindString(12, _tmp_1);
        statement.bindString(13, entity.getImages());
        statement.bindString(14, entity.getAmenities());
        statement.bindString(15, entity.getDescription());
        if (entity.getPurchasePrice() == null) {
          statement.bindNull(16);
        } else {
          statement.bindDouble(16, entity.getPurchasePrice());
        }
        if (entity.getDecorationCost() == null) {
          statement.bindNull(17);
        } else {
          statement.bindDouble(17, entity.getDecorationCost());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfPropertyEntity = new EntityDeletionOrUpdateAdapter<PropertyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `properties` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PropertyEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfPropertyEntity = new EntityDeletionOrUpdateAdapter<PropertyEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `properties` SET `id` = ?,`name` = ?,`address` = ?,`city` = ?,`district` = ?,`latitude` = ?,`longitude` = ?,`type` = ?,`area` = ?,`floor` = ?,`totalRooms` = ?,`status` = ?,`images` = ?,`amenities` = ?,`description` = ?,`purchasePrice` = ?,`decorationCost` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PropertyEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getAddress());
        statement.bindString(4, entity.getCity());
        statement.bindString(5, entity.getDistrict());
        if (entity.getLatitude() == null) {
          statement.bindNull(6);
        } else {
          statement.bindDouble(6, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getLongitude());
        }
        final String _tmp = __converters.fromPropertyType(entity.getType());
        statement.bindString(8, _tmp);
        statement.bindDouble(9, entity.getArea());
        statement.bindString(10, entity.getFloor());
        statement.bindLong(11, entity.getTotalRooms());
        final String _tmp_1 = __converters.fromPropertyStatus(entity.getStatus());
        statement.bindString(12, _tmp_1);
        statement.bindString(13, entity.getImages());
        statement.bindString(14, entity.getAmenities());
        statement.bindString(15, entity.getDescription());
        if (entity.getPurchasePrice() == null) {
          statement.bindNull(16);
        } else {
          statement.bindDouble(16, entity.getPurchasePrice());
        }
        if (entity.getDecorationCost() == null) {
          statement.bindNull(17);
        } else {
          statement.bindDouble(17, entity.getDecorationCost());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
        statement.bindString(20, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePropertyById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM properties WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllProperties = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM properties";
        return _query;
      }
    };
  }

  @Override
  public Object insertProperty(final PropertyEntity property,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPropertyEntity.insert(property);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertProperties(final List<PropertyEntity> properties,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPropertyEntity.insert(properties);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProperty(final PropertyEntity property,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPropertyEntity.handle(property);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProperty(final PropertyEntity property,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPropertyEntity.handle(property);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePropertyById(final String propertyId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePropertyById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, propertyId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePropertyById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllProperties(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllProperties.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAllProperties.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PropertyEntity>> getAllProperties() {
    final String _sql = "SELECT * FROM properties ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"properties"}, new Callable<List<PropertyEntity>>() {
      @Override
      @NonNull
      public List<PropertyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfTotalRooms = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRooms");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfDecorationCost = CursorUtil.getColumnIndexOrThrow(_cursor, "decorationCost");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<PropertyEntity> _result = new ArrayList<PropertyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PropertyEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpDistrict;
            _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final PropertyType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toPropertyType(_tmp);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final int _tmpTotalRooms;
            _tmpTotalRooms = _cursor.getInt(_cursorIndexOfTotalRooms);
            final PropertyStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toPropertyStatus(_tmp_1);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Float _tmpPurchasePrice;
            if (_cursor.isNull(_cursorIndexOfPurchasePrice)) {
              _tmpPurchasePrice = null;
            } else {
              _tmpPurchasePrice = _cursor.getFloat(_cursorIndexOfPurchasePrice);
            }
            final Float _tmpDecorationCost;
            if (_cursor.isNull(_cursorIndexOfDecorationCost)) {
              _tmpDecorationCost = null;
            } else {
              _tmpDecorationCost = _cursor.getFloat(_cursorIndexOfDecorationCost);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new PropertyEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpDistrict,_tmpLatitude,_tmpLongitude,_tmpType,_tmpArea,_tmpFloor,_tmpTotalRooms,_tmpStatus,_tmpImages,_tmpAmenities,_tmpDescription,_tmpPurchasePrice,_tmpDecorationCost,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<PropertyEntity> getPropertyById(final String propertyId) {
    final String _sql = "SELECT * FROM properties WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, propertyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"properties"}, new Callable<PropertyEntity>() {
      @Override
      @Nullable
      public PropertyEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfTotalRooms = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRooms");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfDecorationCost = CursorUtil.getColumnIndexOrThrow(_cursor, "decorationCost");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final PropertyEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpDistrict;
            _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final PropertyType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toPropertyType(_tmp);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final int _tmpTotalRooms;
            _tmpTotalRooms = _cursor.getInt(_cursorIndexOfTotalRooms);
            final PropertyStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toPropertyStatus(_tmp_1);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Float _tmpPurchasePrice;
            if (_cursor.isNull(_cursorIndexOfPurchasePrice)) {
              _tmpPurchasePrice = null;
            } else {
              _tmpPurchasePrice = _cursor.getFloat(_cursorIndexOfPurchasePrice);
            }
            final Float _tmpDecorationCost;
            if (_cursor.isNull(_cursorIndexOfDecorationCost)) {
              _tmpDecorationCost = null;
            } else {
              _tmpDecorationCost = _cursor.getFloat(_cursorIndexOfDecorationCost);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new PropertyEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpDistrict,_tmpLatitude,_tmpLongitude,_tmpType,_tmpArea,_tmpFloor,_tmpTotalRooms,_tmpStatus,_tmpImages,_tmpAmenities,_tmpDescription,_tmpPurchasePrice,_tmpDecorationCost,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PropertyEntity>> getPropertiesByStatus(final PropertyStatus status) {
    final String _sql = "SELECT * FROM properties WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromPropertyStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"properties"}, new Callable<List<PropertyEntity>>() {
      @Override
      @NonNull
      public List<PropertyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfTotalRooms = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRooms");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfDecorationCost = CursorUtil.getColumnIndexOrThrow(_cursor, "decorationCost");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<PropertyEntity> _result = new ArrayList<PropertyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PropertyEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpDistrict;
            _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final PropertyType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toPropertyType(_tmp_1);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final int _tmpTotalRooms;
            _tmpTotalRooms = _cursor.getInt(_cursorIndexOfTotalRooms);
            final PropertyStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toPropertyStatus(_tmp_2);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Float _tmpPurchasePrice;
            if (_cursor.isNull(_cursorIndexOfPurchasePrice)) {
              _tmpPurchasePrice = null;
            } else {
              _tmpPurchasePrice = _cursor.getFloat(_cursorIndexOfPurchasePrice);
            }
            final Float _tmpDecorationCost;
            if (_cursor.isNull(_cursorIndexOfDecorationCost)) {
              _tmpDecorationCost = null;
            } else {
              _tmpDecorationCost = _cursor.getFloat(_cursorIndexOfDecorationCost);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new PropertyEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpDistrict,_tmpLatitude,_tmpLongitude,_tmpType,_tmpArea,_tmpFloor,_tmpTotalRooms,_tmpStatus,_tmpImages,_tmpAmenities,_tmpDescription,_tmpPurchasePrice,_tmpDecorationCost,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<PropertyEntity>> getPropertiesByCity(final String city) {
    final String _sql = "SELECT * FROM properties WHERE city = ? ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, city);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"properties"}, new Callable<List<PropertyEntity>>() {
      @Override
      @NonNull
      public List<PropertyEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfDistrict = CursorUtil.getColumnIndexOrThrow(_cursor, "district");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfTotalRooms = CursorUtil.getColumnIndexOrThrow(_cursor, "totalRooms");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPurchasePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "purchasePrice");
          final int _cursorIndexOfDecorationCost = CursorUtil.getColumnIndexOrThrow(_cursor, "decorationCost");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<PropertyEntity> _result = new ArrayList<PropertyEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PropertyEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpAddress;
            _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            final String _tmpCity;
            _tmpCity = _cursor.getString(_cursorIndexOfCity);
            final String _tmpDistrict;
            _tmpDistrict = _cursor.getString(_cursorIndexOfDistrict);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final PropertyType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toPropertyType(_tmp);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final int _tmpTotalRooms;
            _tmpTotalRooms = _cursor.getInt(_cursorIndexOfTotalRooms);
            final PropertyStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toPropertyStatus(_tmp_1);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final Float _tmpPurchasePrice;
            if (_cursor.isNull(_cursorIndexOfPurchasePrice)) {
              _tmpPurchasePrice = null;
            } else {
              _tmpPurchasePrice = _cursor.getFloat(_cursorIndexOfPurchasePrice);
            }
            final Float _tmpDecorationCost;
            if (_cursor.isNull(_cursorIndexOfDecorationCost)) {
              _tmpDecorationCost = null;
            } else {
              _tmpDecorationCost = _cursor.getFloat(_cursorIndexOfDecorationCost);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new PropertyEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpDistrict,_tmpLatitude,_tmpLongitude,_tmpType,_tmpArea,_tmpFloor,_tmpTotalRooms,_tmpStatus,_tmpImages,_tmpAmenities,_tmpDescription,_tmpPurchasePrice,_tmpDecorationCost,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
