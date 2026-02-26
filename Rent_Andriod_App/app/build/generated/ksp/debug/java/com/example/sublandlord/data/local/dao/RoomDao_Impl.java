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
import com.example.sublandlord.data.local.entity.RoomEntity;
import com.example.sublandlord.data.local.entity.RoomStatus;
import com.example.sublandlord.data.local.entity.RoomType;
import java.lang.Class;
import java.lang.Exception;
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
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RoomEntity> __insertionAdapterOfRoomEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __deletionAdapterOfRoomEntity;

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __updateAdapterOfRoomEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoomById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoomsByPropertyId;

  public RoomDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoomEntity = new EntityInsertionAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `rooms` (`id`,`propertyId`,`roomNumber`,`area`,`floor`,`type`,`currentRentPrice`,`baseRentPrice`,`status`,`amenities`,`images`,`maxOccupants`,`currentOccupants`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getPropertyId());
        statement.bindString(3, entity.getRoomNumber());
        statement.bindDouble(4, entity.getArea());
        statement.bindString(5, entity.getFloor());
        final String _tmp = __converters.fromRoomType(entity.getType());
        statement.bindString(6, _tmp);
        statement.bindDouble(7, entity.getCurrentRentPrice());
        statement.bindDouble(8, entity.getBaseRentPrice());
        final String _tmp_1 = __converters.fromRoomStatus(entity.getStatus());
        statement.bindString(9, _tmp_1);
        statement.bindString(10, entity.getAmenities());
        statement.bindString(11, entity.getImages());
        statement.bindLong(12, entity.getMaxOccupants());
        statement.bindLong(13, entity.getCurrentOccupants());
        statement.bindLong(14, entity.getCreatedAt());
        statement.bindLong(15, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `rooms` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `rooms` SET `id` = ?,`propertyId` = ?,`roomNumber` = ?,`area` = ?,`floor` = ?,`type` = ?,`currentRentPrice` = ?,`baseRentPrice` = ?,`status` = ?,`amenities` = ?,`images` = ?,`maxOccupants` = ?,`currentOccupants` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getPropertyId());
        statement.bindString(3, entity.getRoomNumber());
        statement.bindDouble(4, entity.getArea());
        statement.bindString(5, entity.getFloor());
        final String _tmp = __converters.fromRoomType(entity.getType());
        statement.bindString(6, _tmp);
        statement.bindDouble(7, entity.getCurrentRentPrice());
        statement.bindDouble(8, entity.getBaseRentPrice());
        final String _tmp_1 = __converters.fromRoomStatus(entity.getStatus());
        statement.bindString(9, _tmp_1);
        statement.bindString(10, entity.getAmenities());
        statement.bindString(11, entity.getImages());
        statement.bindLong(12, entity.getMaxOccupants());
        statement.bindLong(13, entity.getCurrentOccupants());
        statement.bindLong(14, entity.getCreatedAt());
        statement.bindLong(15, entity.getUpdatedAt());
        statement.bindString(16, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteRoomById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM rooms WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteRoomsByPropertyId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM rooms WHERE propertyId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomEntity.insert(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRooms(final List<RoomEntity> rooms,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomEntity.insert(rooms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoomById(final String roomId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoomById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, roomId);
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
          __preparedStmtOfDeleteRoomById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoomsByPropertyId(final String propertyId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoomsByPropertyId.acquire();
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
          __preparedStmtOfDeleteRoomsByPropertyId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RoomEntity>> getRoomsByPropertyId(final String propertyId) {
    final String _sql = "SELECT * FROM rooms WHERE propertyId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, propertyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCurrentRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRentPrice");
          final int _cursorIndexOfBaseRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "baseRentPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfMaxOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "maxOccupants");
          final int _cursorIndexOfCurrentOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "currentOccupants");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpRoomNumber;
            _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toRoomType(_tmp);
            final float _tmpCurrentRentPrice;
            _tmpCurrentRentPrice = _cursor.getFloat(_cursorIndexOfCurrentRentPrice);
            final float _tmpBaseRentPrice;
            _tmpBaseRentPrice = _cursor.getFloat(_cursorIndexOfBaseRentPrice);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final int _tmpMaxOccupants;
            _tmpMaxOccupants = _cursor.getInt(_cursorIndexOfMaxOccupants);
            final int _tmpCurrentOccupants;
            _tmpCurrentOccupants = _cursor.getInt(_cursorIndexOfCurrentOccupants);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new RoomEntity(_tmpId,_tmpPropertyId,_tmpRoomNumber,_tmpArea,_tmpFloor,_tmpType,_tmpCurrentRentPrice,_tmpBaseRentPrice,_tmpStatus,_tmpAmenities,_tmpImages,_tmpMaxOccupants,_tmpCurrentOccupants,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<RoomEntity> getRoomById(final String roomId) {
    final String _sql = "SELECT * FROM rooms WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<RoomEntity>() {
      @Override
      @Nullable
      public RoomEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCurrentRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRentPrice");
          final int _cursorIndexOfBaseRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "baseRentPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfMaxOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "maxOccupants");
          final int _cursorIndexOfCurrentOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "currentOccupants");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final RoomEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpRoomNumber;
            _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toRoomType(_tmp);
            final float _tmpCurrentRentPrice;
            _tmpCurrentRentPrice = _cursor.getFloat(_cursorIndexOfCurrentRentPrice);
            final float _tmpBaseRentPrice;
            _tmpBaseRentPrice = _cursor.getFloat(_cursorIndexOfBaseRentPrice);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final int _tmpMaxOccupants;
            _tmpMaxOccupants = _cursor.getInt(_cursorIndexOfMaxOccupants);
            final int _tmpCurrentOccupants;
            _tmpCurrentOccupants = _cursor.getInt(_cursorIndexOfCurrentOccupants);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new RoomEntity(_tmpId,_tmpPropertyId,_tmpRoomNumber,_tmpArea,_tmpFloor,_tmpType,_tmpCurrentRentPrice,_tmpBaseRentPrice,_tmpStatus,_tmpAmenities,_tmpImages,_tmpMaxOccupants,_tmpCurrentOccupants,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<RoomEntity>> getRoomsByStatus(final RoomStatus status) {
    final String _sql = "SELECT * FROM rooms WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromRoomStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCurrentRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRentPrice");
          final int _cursorIndexOfBaseRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "baseRentPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfMaxOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "maxOccupants");
          final int _cursorIndexOfCurrentOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "currentOccupants");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpRoomNumber;
            _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toRoomType(_tmp_1);
            final float _tmpCurrentRentPrice;
            _tmpCurrentRentPrice = _cursor.getFloat(_cursorIndexOfCurrentRentPrice);
            final float _tmpBaseRentPrice;
            _tmpBaseRentPrice = _cursor.getFloat(_cursorIndexOfBaseRentPrice);
            final RoomStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toRoomStatus(_tmp_2);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final int _tmpMaxOccupants;
            _tmpMaxOccupants = _cursor.getInt(_cursorIndexOfMaxOccupants);
            final int _tmpCurrentOccupants;
            _tmpCurrentOccupants = _cursor.getInt(_cursorIndexOfCurrentOccupants);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new RoomEntity(_tmpId,_tmpPropertyId,_tmpRoomNumber,_tmpArea,_tmpFloor,_tmpType,_tmpCurrentRentPrice,_tmpBaseRentPrice,_tmpStatus,_tmpAmenities,_tmpImages,_tmpMaxOccupants,_tmpCurrentOccupants,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<RoomEntity>> getRoomsByPropertyAndStatus(final String propertyId,
      final RoomStatus status) {
    final String _sql = "SELECT * FROM rooms WHERE propertyId = ? AND status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, propertyId);
    _argIndex = 2;
    final String _tmp = __converters.fromRoomStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfArea = CursorUtil.getColumnIndexOrThrow(_cursor, "area");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCurrentRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRentPrice");
          final int _cursorIndexOfBaseRentPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "baseRentPrice");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAmenities = CursorUtil.getColumnIndexOrThrow(_cursor, "amenities");
          final int _cursorIndexOfImages = CursorUtil.getColumnIndexOrThrow(_cursor, "images");
          final int _cursorIndexOfMaxOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "maxOccupants");
          final int _cursorIndexOfCurrentOccupants = CursorUtil.getColumnIndexOrThrow(_cursor, "currentOccupants");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpRoomNumber;
            _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            final float _tmpArea;
            _tmpArea = _cursor.getFloat(_cursorIndexOfArea);
            final String _tmpFloor;
            _tmpFloor = _cursor.getString(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toRoomType(_tmp_1);
            final float _tmpCurrentRentPrice;
            _tmpCurrentRentPrice = _cursor.getFloat(_cursorIndexOfCurrentRentPrice);
            final float _tmpBaseRentPrice;
            _tmpBaseRentPrice = _cursor.getFloat(_cursorIndexOfBaseRentPrice);
            final RoomStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toRoomStatus(_tmp_2);
            final String _tmpAmenities;
            _tmpAmenities = _cursor.getString(_cursorIndexOfAmenities);
            final String _tmpImages;
            _tmpImages = _cursor.getString(_cursorIndexOfImages);
            final int _tmpMaxOccupants;
            _tmpMaxOccupants = _cursor.getInt(_cursorIndexOfMaxOccupants);
            final int _tmpCurrentOccupants;
            _tmpCurrentOccupants = _cursor.getInt(_cursorIndexOfCurrentOccupants);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new RoomEntity(_tmpId,_tmpPropertyId,_tmpRoomNumber,_tmpArea,_tmpFloor,_tmpType,_tmpCurrentRentPrice,_tmpBaseRentPrice,_tmpStatus,_tmpAmenities,_tmpImages,_tmpMaxOccupants,_tmpCurrentOccupants,_tmpCreatedAt,_tmpUpdatedAt);
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
