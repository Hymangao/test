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
import com.example.sublandlord.data.local.entity.Contact;
import com.example.sublandlord.data.local.entity.Document;
import com.example.sublandlord.data.local.entity.TenantEntity;
import com.example.sublandlord.data.local.entity.TenantStatus;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class TenantDao_Impl implements TenantDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TenantEntity> __insertionAdapterOfTenantEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<TenantEntity> __deletionAdapterOfTenantEntity;

  private final EntityDeletionOrUpdateAdapter<TenantEntity> __updateAdapterOfTenantEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTenantById;

  private final SharedSQLiteStatement __preparedStmtOfVacateRoom;

  public TenantDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTenantEntity = new EntityInsertionAdapter<TenantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `tenants` (`id`,`name`,`phone`,`idCard`,`wechat`,`emergencyContact`,`roomId`,`status`,`creditScore`,`documents`,`notes`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TenantEntity entity) {
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
        final String _tmp = __converters.fromContact(entity.getEmergencyContact());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getRoomId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRoomId());
        }
        final String _tmp_1 = __converters.fromTenantStatus(entity.getStatus());
        statement.bindString(8, _tmp_1);
        if (entity.getCreditScore() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getCreditScore());
        }
        final String _tmp_2 = __converters.fromDocumentList(entity.getDocuments());
        statement.bindString(10, _tmp_2);
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNotes());
        }
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfTenantEntity = new EntityDeletionOrUpdateAdapter<TenantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `tenants` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TenantEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfTenantEntity = new EntityDeletionOrUpdateAdapter<TenantEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `tenants` SET `id` = ?,`name` = ?,`phone` = ?,`idCard` = ?,`wechat` = ?,`emergencyContact` = ?,`roomId` = ?,`status` = ?,`creditScore` = ?,`documents` = ?,`notes` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TenantEntity entity) {
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
        final String _tmp = __converters.fromContact(entity.getEmergencyContact());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getRoomId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRoomId());
        }
        final String _tmp_1 = __converters.fromTenantStatus(entity.getStatus());
        statement.bindString(8, _tmp_1);
        if (entity.getCreditScore() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getCreditScore());
        }
        final String _tmp_2 = __converters.fromDocumentList(entity.getDocuments());
        statement.bindString(10, _tmp_2);
        if (entity.getNotes() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getNotes());
        }
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        statement.bindString(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteTenantById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM tenants WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfVacateRoom = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE tenants SET roomId = NULL WHERE roomId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTenant(final TenantEntity tenant,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTenantEntity.insert(tenant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTenants(final List<TenantEntity> tenants,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTenantEntity.insert(tenants);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTenant(final TenantEntity tenant,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTenantEntity.handle(tenant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTenant(final TenantEntity tenant,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTenantEntity.handle(tenant);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTenantById(final String tenantId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTenantById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, tenantId);
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
          __preparedStmtOfDeleteTenantById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object vacateRoom(final String roomId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfVacateRoom.acquire();
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
          __preparedStmtOfVacateRoom.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TenantEntity>> getAllTenants() {
    final String _sql = "SELECT * FROM tenants ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tenants"}, new Callable<List<TenantEntity>>() {
      @Override
      @NonNull
      public List<TenantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfEmergencyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "emergencyContact");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreditScore = CursorUtil.getColumnIndexOrThrow(_cursor, "creditScore");
          final int _cursorIndexOfDocuments = CursorUtil.getColumnIndexOrThrow(_cursor, "documents");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TenantEntity> _result = new ArrayList<TenantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TenantEntity _item;
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
            final Contact _tmpEmergencyContact;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfEmergencyContact)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfEmergencyContact);
            }
            if (_tmp == null) {
              _tmpEmergencyContact = null;
            } else {
              _tmpEmergencyContact = __converters.toContact(_tmp);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final TenantStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTenantStatus(_tmp_1);
            final Integer _tmpCreditScore;
            if (_cursor.isNull(_cursorIndexOfCreditScore)) {
              _tmpCreditScore = null;
            } else {
              _tmpCreditScore = _cursor.getInt(_cursorIndexOfCreditScore);
            }
            final List<Document> _tmpDocuments;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDocuments);
            _tmpDocuments = __converters.toDocumentList(_tmp_2);
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
            _item = new TenantEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpEmergencyContact,_tmpRoomId,_tmpStatus,_tmpCreditScore,_tmpDocuments,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<TenantEntity> getTenantById(final String tenantId) {
    final String _sql = "SELECT * FROM tenants WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tenantId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tenants"}, new Callable<TenantEntity>() {
      @Override
      @Nullable
      public TenantEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfEmergencyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "emergencyContact");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreditScore = CursorUtil.getColumnIndexOrThrow(_cursor, "creditScore");
          final int _cursorIndexOfDocuments = CursorUtil.getColumnIndexOrThrow(_cursor, "documents");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TenantEntity _result;
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
            final Contact _tmpEmergencyContact;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfEmergencyContact)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfEmergencyContact);
            }
            if (_tmp == null) {
              _tmpEmergencyContact = null;
            } else {
              _tmpEmergencyContact = __converters.toContact(_tmp);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final TenantStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTenantStatus(_tmp_1);
            final Integer _tmpCreditScore;
            if (_cursor.isNull(_cursorIndexOfCreditScore)) {
              _tmpCreditScore = null;
            } else {
              _tmpCreditScore = _cursor.getInt(_cursorIndexOfCreditScore);
            }
            final List<Document> _tmpDocuments;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDocuments);
            _tmpDocuments = __converters.toDocumentList(_tmp_2);
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
            _result = new TenantEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpEmergencyContact,_tmpRoomId,_tmpStatus,_tmpCreditScore,_tmpDocuments,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TenantEntity>> getTenantsByRoomId(final String roomId) {
    final String _sql = "SELECT * FROM tenants WHERE roomId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tenants"}, new Callable<List<TenantEntity>>() {
      @Override
      @NonNull
      public List<TenantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfEmergencyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "emergencyContact");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreditScore = CursorUtil.getColumnIndexOrThrow(_cursor, "creditScore");
          final int _cursorIndexOfDocuments = CursorUtil.getColumnIndexOrThrow(_cursor, "documents");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TenantEntity> _result = new ArrayList<TenantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TenantEntity _item;
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
            final Contact _tmpEmergencyContact;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfEmergencyContact)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfEmergencyContact);
            }
            if (_tmp == null) {
              _tmpEmergencyContact = null;
            } else {
              _tmpEmergencyContact = __converters.toContact(_tmp);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final TenantStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTenantStatus(_tmp_1);
            final Integer _tmpCreditScore;
            if (_cursor.isNull(_cursorIndexOfCreditScore)) {
              _tmpCreditScore = null;
            } else {
              _tmpCreditScore = _cursor.getInt(_cursorIndexOfCreditScore);
            }
            final List<Document> _tmpDocuments;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDocuments);
            _tmpDocuments = __converters.toDocumentList(_tmp_2);
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
            _item = new TenantEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpEmergencyContact,_tmpRoomId,_tmpStatus,_tmpCreditScore,_tmpDocuments,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TenantEntity>> getTenantsByStatus(final TenantStatus status) {
    final String _sql = "SELECT * FROM tenants WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromTenantStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tenants"}, new Callable<List<TenantEntity>>() {
      @Override
      @NonNull
      public List<TenantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfEmergencyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "emergencyContact");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreditScore = CursorUtil.getColumnIndexOrThrow(_cursor, "creditScore");
          final int _cursorIndexOfDocuments = CursorUtil.getColumnIndexOrThrow(_cursor, "documents");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TenantEntity> _result = new ArrayList<TenantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TenantEntity _item;
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
            final Contact _tmpEmergencyContact;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfEmergencyContact)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfEmergencyContact);
            }
            if (_tmp_1 == null) {
              _tmpEmergencyContact = null;
            } else {
              _tmpEmergencyContact = __converters.toContact(_tmp_1);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final TenantStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTenantStatus(_tmp_2);
            final Integer _tmpCreditScore;
            if (_cursor.isNull(_cursorIndexOfCreditScore)) {
              _tmpCreditScore = null;
            } else {
              _tmpCreditScore = _cursor.getInt(_cursorIndexOfCreditScore);
            }
            final List<Document> _tmpDocuments;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfDocuments);
            _tmpDocuments = __converters.toDocumentList(_tmp_3);
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
            _item = new TenantEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpEmergencyContact,_tmpRoomId,_tmpStatus,_tmpCreditScore,_tmpDocuments,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TenantEntity>> searchTenants(final String query) {
    final String _sql = "SELECT * FROM tenants WHERE name LIKE '%' || ? || '%' OR phone LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"tenants"}, new Callable<List<TenantEntity>>() {
      @Override
      @NonNull
      public List<TenantEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfIdCard = CursorUtil.getColumnIndexOrThrow(_cursor, "idCard");
          final int _cursorIndexOfWechat = CursorUtil.getColumnIndexOrThrow(_cursor, "wechat");
          final int _cursorIndexOfEmergencyContact = CursorUtil.getColumnIndexOrThrow(_cursor, "emergencyContact");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCreditScore = CursorUtil.getColumnIndexOrThrow(_cursor, "creditScore");
          final int _cursorIndexOfDocuments = CursorUtil.getColumnIndexOrThrow(_cursor, "documents");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TenantEntity> _result = new ArrayList<TenantEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TenantEntity _item;
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
            final Contact _tmpEmergencyContact;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfEmergencyContact)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfEmergencyContact);
            }
            if (_tmp == null) {
              _tmpEmergencyContact = null;
            } else {
              _tmpEmergencyContact = __converters.toContact(_tmp);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final TenantStatus _tmpStatus;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTenantStatus(_tmp_1);
            final Integer _tmpCreditScore;
            if (_cursor.isNull(_cursorIndexOfCreditScore)) {
              _tmpCreditScore = null;
            } else {
              _tmpCreditScore = _cursor.getInt(_cursorIndexOfCreditScore);
            }
            final List<Document> _tmpDocuments;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfDocuments);
            _tmpDocuments = __converters.toDocumentList(_tmp_2);
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
            _item = new TenantEntity(_tmpId,_tmpName,_tmpPhone,_tmpIdCard,_tmpWechat,_tmpEmergencyContact,_tmpRoomId,_tmpStatus,_tmpCreditScore,_tmpDocuments,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
