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
import com.example.sublandlord.data.local.entity.LandlordEntity;
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
public final class LandlordDao_Impl implements LandlordDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LandlordEntity> __insertionAdapterOfLandlordEntity;

  private final EntityDeletionOrUpdateAdapter<LandlordEntity> __deletionAdapterOfLandlordEntity;

  private final EntityDeletionOrUpdateAdapter<LandlordEntity> __updateAdapterOfLandlordEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLandlordById;

  public LandlordDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLandlordEntity = new EntityInsertionAdapter<LandlordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `landlords` (`id`,`name`,`phone`,`idCard`,`wechat`,`bankInfo`,`notes`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        if (entity.getIdCard() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIdCard());
        }
        if (entity.getWechat() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getWechat());
        }
        if (entity.getBankInfo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBankInfo());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfLandlordEntity = new EntityDeletionOrUpdateAdapter<LandlordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `landlords` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfLandlordEntity = new EntityDeletionOrUpdateAdapter<LandlordEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `landlords` SET `id` = ?,`name` = ?,`phone` = ?,`idCard` = ?,`wechat` = ?,`bankInfo` = ?,`notes` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        statement.bindString(3, entity.getPhone());
        if (entity.getIdCard() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIdCard());
        }
        if (entity.getWechat() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getWechat());
        }
        if (entity.getBankInfo() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBankInfo());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        statement.bindString(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteLandlordById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM landlords WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertLandlord(final LandlordEntity landlord,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLandlordEntity.insert(landlord);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLandlords(final List<LandlordEntity> landlords,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLandlordEntity.insert(landlords);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLandlord(final LandlordEntity landlord,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLandlordEntity.handle(landlord);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLandlord(final LandlordEntity landlord,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLandlordEntity.handle(landlord);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLandlordById(final String landlordId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLandlordById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, landlordId);
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
          __preparedStmtOfDeleteLandlordById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LandlordEntity>> getAllLandlords() {
    final String _sql = "SELECT * FROM landlords ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlords"}, new Callable<List<LandlordEntity>>() {
      @Override
      @NonNull
      public List<LandlordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfBankInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "bankInfo");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordEntity> _result = new ArrayList<LandlordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpIdCard;
            if (_cursor.isNull(_cursorIndexOfIdCard)) {
              _tmpIdCard = null;
            } else {
              _tmpIdCard = _cursor.getString(_cursorIndexOfIdCard);
            }
            final String _tmpWechat;
            if (_cursor.isNull(_cursorIndexOfWechat)) {
              _tmpWechat = null;
            } else {
              _tmpWechat = _cursor.getString(_cursorIndexOfWechat);
            }
            final String _tmpBankInfo;
            if (_cursor.isNull(_cursorIndexOfBankInfo)) {
              _tmpBankInfo = null;
            } else {
              _tmpBankInfo = _cursor.getString(_cursorIndexOfBankInfo);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new LandlordEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpBankInfo,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<LandlordEntity> getLandlordById(final String landlordId) {
    final String _sql = "SELECT * FROM landlords WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, landlordId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlords"}, new Callable<LandlordEntity>() {
      @Override
      @Nullable
      public LandlordEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfBankInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "bankInfo");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final LandlordEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpIdCard;
            if (_cursor.isNull(_cursorIndexOfIdCard)) {
              _tmpIdCard = null;
            } else {
              _tmpIdCard = _cursor.getString(_cursorIndexOfIdCard);
            }
            final String _tmpWechat;
            if (_cursor.isNull(_cursorIndexOfWechat)) {
              _tmpWechat = null;
            } else {
              _tmpWechat = _cursor.getString(_cursorIndexOfWechat);
            }
            final String _tmpBankInfo;
            if (_cursor.isNull(_cursorIndexOfBankInfo)) {
              _tmpBankInfo = null;
            } else {
              _tmpBankInfo = _cursor.getString(_cursorIndexOfBankInfo);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new LandlordEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpBankInfo,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LandlordEntity>> searchLandlords(final String query) {
    final String _sql = "SELECT * FROM landlords WHERE name LIKE '%' || ? || '%' OR phone LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlords"}, new Callable<List<LandlordEntity>>() {
      @Override
      @NonNull
      public List<LandlordEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfBankInfo = CursorUtil.getColumnIndexOrThrow(_cursor, "bankInfo");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordEntity> _result = new ArrayList<LandlordEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpPhone;
            _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            final String _tmpIdCard;
            if (_cursor.isNull(_cursorIndexOfIdCard)) {
              _tmpIdCard = null;
            } else {
              _tmpIdCard = _cursor.getString(_cursorIndexOfIdCard);
            }
            final String _tmpWechat;
            if (_cursor.isNull(_cursorIndexOfWechat)) {
              _tmpWechat = null;
            } else {
              _tmpWechat = _cursor.getString(_cursorIndexOfWechat);
            }
            final String _tmpBankInfo;
            if (_cursor.isNull(_cursorIndexOfBankInfo)) {
              _tmpBankInfo = null;
            } else {
              _tmpBankInfo = _cursor.getString(_cursorIndexOfBankInfo);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new LandlordEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpBankInfo,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
