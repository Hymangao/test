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
import com.example.sublandlord.data.local.entity.RelatedEntityType;
import com.example.sublandlord.data.local.entity.ReminderEntity;
import com.example.sublandlord.data.local.entity.ReminderPriority;
import com.example.sublandlord.data.local.entity.ReminderStatus;
import com.example.sublandlord.data.local.entity.ReminderType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
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
public final class ReminderDao_Impl implements ReminderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReminderEntity> __insertionAdapterOfReminderEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __deletionAdapterOfReminderEntity;

  private final EntityDeletionOrUpdateAdapter<ReminderEntity> __updateAdapterOfReminderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReminderById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCompletedReminders;

  public ReminderDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReminderEntity = new EntityInsertionAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `reminders` (`id`,`type`,`title`,`message`,`targetDate`,`advanceDays`,`relatedEntityId`,`relatedEntityType`,`status`,`priority`,`sentDates`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromReminderType(entity.getType());
        statement.bindString(2, _tmp);
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getMessage());
        statement.bindLong(5, entity.getTargetDate());
        if (entity.getAdvanceDays() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getAdvanceDays());
        }
        if (entity.getRelatedEntityId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRelatedEntityId());
        }
        final String _tmp_1;
        if (entity.getRelatedEntityType() == null) {
          _tmp_1 = null;
        } else {
          _tmp_1 = __converters.fromRelatedEntityType(entity.getRelatedEntityType());
        }
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __converters.fromReminderStatus(entity.getStatus());
        statement.bindString(9, _tmp_2);
        final String _tmp_3 = __converters.fromReminderPriority(entity.getPriority());
        statement.bindString(10, _tmp_3);
        final String _tmp_4 = __converters.fromLongList(entity.getSentDates());
        statement.bindString(11, _tmp_4);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `reminders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfReminderEntity = new EntityDeletionOrUpdateAdapter<ReminderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `reminders` SET `id` = ?,`type` = ?,`title` = ?,`message` = ?,`targetDate` = ?,`advanceDays` = ?,`relatedEntityId` = ?,`relatedEntityType` = ?,`status` = ?,`priority` = ?,`sentDates` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReminderEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromReminderType(entity.getType());
        statement.bindString(2, _tmp);
        statement.bindString(3, entity.getTitle());
        statement.bindString(4, entity.getMessage());
        statement.bindLong(5, entity.getTargetDate());
        if (entity.getAdvanceDays() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getAdvanceDays());
        }
        if (entity.getRelatedEntityId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRelatedEntityId());
        }
        final String _tmp_1;
        if (entity.getRelatedEntityType() == null) {
          _tmp_1 = null;
        } else {
          _tmp_1 = __converters.fromRelatedEntityType(entity.getRelatedEntityType());
        }
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __converters.fromReminderStatus(entity.getStatus());
        statement.bindString(9, _tmp_2);
        final String _tmp_3 = __converters.fromReminderPriority(entity.getPriority());
        statement.bindString(10, _tmp_3);
        final String _tmp_4 = __converters.fromLongList(entity.getSentDates());
        statement.bindString(11, _tmp_4);
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        statement.bindString(14, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteReminderById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM reminders WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteCompletedReminders = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM reminders WHERE status = 'COMPLETED'";
        return _query;
      }
    };
  }

  @Override
  public Object insertReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReminderEntity.insert(reminder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertReminders(final List<ReminderEntity> reminders,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReminderEntity.insert(reminders);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReminderEntity.handle(reminder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateReminder(final ReminderEntity reminder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReminderEntity.handle(reminder);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReminderById(final String reminderId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReminderById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, reminderId);
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
          __preparedStmtOfDeleteReminderById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCompletedReminders(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCompletedReminders.acquire();
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
          __preparedStmtOfDeleteCompletedReminders.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ReminderEntity>> getAllReminders() {
    final String _sql = "SELECT * FROM reminders ORDER BY targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_1 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_1);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_2);
            final ReminderPriority _tmpPriority;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_3);
            final List<Long> _tmpSentDates;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<ReminderEntity> getReminderById(final String reminderId) {
    final String _sql = "SELECT * FROM reminders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, reminderId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<ReminderEntity>() {
      @Override
      @Nullable
      public ReminderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ReminderEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_1 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_1);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_2);
            final ReminderPriority _tmpPriority;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_3);
            final List<Long> _tmpSentDates;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ReminderEntity>> getRemindersByType(final ReminderType type) {
    final String _sql = "SELECT * FROM reminders WHERE type = ? ORDER BY targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromReminderType(type);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp_1);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_2 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_2);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_3);
            final ReminderPriority _tmpPriority;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_4);
            final List<Long> _tmpSentDates;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ReminderEntity>> getRemindersByStatus(final ReminderStatus status) {
    final String _sql = "SELECT * FROM reminders WHERE status = ? ORDER BY targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromReminderStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp_1);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_2 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_2);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_3);
            final ReminderPriority _tmpPriority;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_4);
            final List<Long> _tmpSentDates;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ReminderEntity>> getRemindersByPriority(final ReminderPriority priority) {
    final String _sql = "SELECT * FROM reminders WHERE priority = ? ORDER BY targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromReminderPriority(priority);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp_1);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_2 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_2);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_3);
            final ReminderPriority _tmpPriority;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_4);
            final List<Long> _tmpSentDates;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ReminderEntity>> getDueReminders(final long timestamp) {
    final String _sql = "SELECT * FROM reminders WHERE targetDate <= ? AND status = 'PENDING'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_1 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_1);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_2);
            final ReminderPriority _tmpPriority;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_3);
            final List<Long> _tmpSentDates;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ReminderEntity>> getRemindersByEntity(final String entityId,
      final RelatedEntityType entityType) {
    final String _sql = "SELECT * FROM reminders WHERE relatedEntityId = ? AND relatedEntityType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, entityId);
    _argIndex = 2;
    final String _tmp = __converters.fromRelatedEntityType(entityType);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"reminders"}, new Callable<List<ReminderEntity>>() {
      @Override
      @NonNull
      public List<ReminderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfAdvanceDays = CursorUtil.getColumnIndexOrThrow(_cursor, "advanceDays");
          final int _cursorIndexOfRelatedEntityId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityId");
          final int _cursorIndexOfRelatedEntityType = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedEntityType");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfSentDates = CursorUtil.getColumnIndexOrThrow(_cursor, "sentDates");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ReminderEntity> _result = new ArrayList<ReminderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReminderEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final ReminderType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toReminderType(_tmp_1);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final String _tmpMessage;
            _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
            final long _tmpTargetDate;
            _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            final Integer _tmpAdvanceDays;
            if (_cursor.isNull(_cursorIndexOfAdvanceDays)) {
              _tmpAdvanceDays = null;
            } else {
              _tmpAdvanceDays = _cursor.getInt(_cursorIndexOfAdvanceDays);
            }
            final String _tmpRelatedEntityId;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityId)) {
              _tmpRelatedEntityId = null;
            } else {
              _tmpRelatedEntityId = _cursor.getString(_cursorIndexOfRelatedEntityId);
            }
            final RelatedEntityType _tmpRelatedEntityType;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfRelatedEntityType)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfRelatedEntityType);
            }
            if (_tmp_2 == null) {
              _tmpRelatedEntityType = null;
            } else {
              _tmpRelatedEntityType = __converters.toRelatedEntityType(_tmp_2);
            }
            final ReminderStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toReminderStatus(_tmp_3);
            final ReminderPriority _tmpPriority;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPriority);
            _tmpPriority = __converters.toReminderPriority(_tmp_4);
            final List<Long> _tmpSentDates;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfSentDates);
            _tmpSentDates = __converters.toLongList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ReminderEntity(_tmpId,_tmpType,_tmpTitle,_tmpMessage,_tmpTargetDate,_tmpAdvanceDays,_tmpRelatedEntityId,_tmpRelatedEntityType,_tmpStatus,_tmpPriority,_tmpSentDates,_tmpCreatedAt,_tmpUpdatedAt);
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
