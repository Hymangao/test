package com.example.sublandlord.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
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
import com.example.sublandlord.data.local.entity.PaymentMethod;
import com.example.sublandlord.data.local.entity.TransactionCategory;
import com.example.sublandlord.data.local.entity.TransactionEntity;
import com.example.sublandlord.data.local.entity.TransactionStatus;
import com.example.sublandlord.data.local.entity.TransactionType;
import java.lang.Class;
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
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionEntity> __insertionAdapterOfTransactionEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __deletionAdapterOfTransactionEntity;

  private final EntityDeletionOrUpdateAdapter<TransactionEntity> __updateAdapterOfTransactionEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTransactionById;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionEntity = new EntityInsertionAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`type`,`category`,`amount`,`date`,`propertyId`,`roomId`,`contractId`,`relatedPartyId`,`relatedPartyName`,`status`,`paymentMethod`,`proofImages`,`description`,`tags`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromTransactionType(entity.getType());
        statement.bindString(2, _tmp);
        final String _tmp_1 = __converters.fromTransactionCategory(entity.getCategory());
        statement.bindString(3, _tmp_1);
        statement.bindDouble(4, entity.getAmount());
        statement.bindLong(5, entity.getDate());
        if (entity.getPropertyId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPropertyId());
        }
        if (entity.getRoomId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRoomId());
        }
        if (entity.getContractId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getContractId());
        }
        if (entity.getRelatedPartyId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getRelatedPartyId());
        }
        if (entity.getRelatedPartyName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRelatedPartyName());
        }
        final String _tmp_2 = __converters.fromTransactionStatus(entity.getStatus());
        statement.bindString(11, _tmp_2);
        final String _tmp_3 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(12, _tmp_3);
        statement.bindString(13, entity.getProofImages());
        statement.bindString(14, entity.getDescription());
        final String _tmp_4 = __converters.fromStringList(entity.getTags());
        statement.bindString(15, _tmp_4);
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransactionEntity = new EntityDeletionOrUpdateAdapter<TransactionEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`type` = ?,`category` = ?,`amount` = ?,`date` = ?,`propertyId` = ?,`roomId` = ?,`contractId` = ?,`relatedPartyId` = ?,`relatedPartyName` = ?,`status` = ?,`paymentMethod` = ?,`proofImages` = ?,`description` = ?,`tags` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromTransactionType(entity.getType());
        statement.bindString(2, _tmp);
        final String _tmp_1 = __converters.fromTransactionCategory(entity.getCategory());
        statement.bindString(3, _tmp_1);
        statement.bindDouble(4, entity.getAmount());
        statement.bindLong(5, entity.getDate());
        if (entity.getPropertyId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPropertyId());
        }
        if (entity.getRoomId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getRoomId());
        }
        if (entity.getContractId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getContractId());
        }
        if (entity.getRelatedPartyId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getRelatedPartyId());
        }
        if (entity.getRelatedPartyName() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRelatedPartyName());
        }
        final String _tmp_2 = __converters.fromTransactionStatus(entity.getStatus());
        statement.bindString(11, _tmp_2);
        final String _tmp_3 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(12, _tmp_3);
        statement.bindString(13, entity.getProofImages());
        statement.bindString(14, entity.getDescription());
        final String _tmp_4 = __converters.fromStringList(entity.getTags());
        statement.bindString(15, _tmp_4);
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getUpdatedAt());
        statement.bindString(18, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteTransactionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionEntity.insert(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTransactions(final List<TransactionEntity> transactions,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionEntity.insert(transactions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransaction(final TransactionEntity transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionEntity.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransactionById(final String transactionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTransactionById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, transactionId);
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
          __preparedStmtOfDeleteTransactionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<TransactionEntity>> getAllTransactions() {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp);
            final TransactionCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_1);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_2);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_3);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<TransactionEntity> getTransactionById(final String transactionId) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, transactionId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<TransactionEntity>() {
      @Override
      @Nullable
      public TransactionEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TransactionEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp);
            final TransactionCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_1);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_2);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_3);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByType(final TransactionType type) {
    final String _sql = "SELECT * FROM transactions WHERE type = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromTransactionType(type);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp_1);
            final TransactionCategory _tmpCategory;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_2);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_3);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_4);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByCategory(
      final TransactionCategory category) {
    final String _sql = "SELECT * FROM transactions WHERE category = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromTransactionCategory(category);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp_1);
            final TransactionCategory _tmpCategory;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_2);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_3);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_4);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByPropertyId(final String propertyId) {
    final String _sql = "SELECT * FROM transactions WHERE propertyId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, propertyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp);
            final TransactionCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_1);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_2);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_3);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByRoomId(final String roomId) {
    final String _sql = "SELECT * FROM transactions WHERE roomId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp);
            final TransactionCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_1);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_2);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_3);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByContractId(final String contractId) {
    final String _sql = "SELECT * FROM transactions WHERE contractId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, contractId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp);
            final TransactionCategory _tmpCategory;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_1);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_2);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_3);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_4);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<TransactionEntity>> getTransactionsByStatus(final TransactionStatus status) {
    final String _sql = "SELECT * FROM transactions WHERE status = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromTransactionStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"transactions"}, new Callable<List<TransactionEntity>>() {
      @Override
      @NonNull
      public List<TransactionEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfContractId = CursorUtil.getColumnIndexOrThrow(_cursor, "contractId");
          final int _cursorIndexOfRelatedPartyId = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyId");
          final int _cursorIndexOfRelatedPartyName = CursorUtil.getColumnIndexOrThrow(_cursor, "relatedPartyName");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfProofImages = CursorUtil.getColumnIndexOrThrow(_cursor, "proofImages");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionEntity> _result = new ArrayList<TransactionEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final TransactionType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toTransactionType(_tmp_1);
            final TransactionCategory _tmpCategory;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfCategory);
            _tmpCategory = __converters.toTransactionCategory(_tmp_2);
            final float _tmpAmount;
            _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            final String _tmpPropertyId;
            if (_cursor.isNull(_cursorIndexOfPropertyId)) {
              _tmpPropertyId = null;
            } else {
              _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            }
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpContractId;
            if (_cursor.isNull(_cursorIndexOfContractId)) {
              _tmpContractId = null;
            } else {
              _tmpContractId = _cursor.getString(_cursorIndexOfContractId);
            }
            final String _tmpRelatedPartyId;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyId)) {
              _tmpRelatedPartyId = null;
            } else {
              _tmpRelatedPartyId = _cursor.getString(_cursorIndexOfRelatedPartyId);
            }
            final String _tmpRelatedPartyName;
            if (_cursor.isNull(_cursorIndexOfRelatedPartyName)) {
              _tmpRelatedPartyName = null;
            } else {
              _tmpRelatedPartyName = _cursor.getString(_cursorIndexOfRelatedPartyName);
            }
            final TransactionStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toTransactionStatus(_tmp_3);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_4);
            final String _tmpProofImages;
            _tmpProofImages = _cursor.getString(_cursorIndexOfProofImages);
            final String _tmpDescription;
            _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            final List<String> _tmpTags;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfTags);
            _tmpTags = __converters.toStringList(_tmp_5);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionEntity(_tmpId,_tmpType,_tmpCategory,_tmpAmount,_tmpDate,_tmpPropertyId,_tmpRoomId,_tmpContractId,_tmpRelatedPartyId,_tmpRelatedPartyName,_tmpStatus,_tmpPaymentMethod,_tmpProofImages,_tmpDescription,_tmpTags,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getTotalIncome(final long startTime, final long endTime,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT SUM(amount) FROM transactions WHERE type = 'INCOME' AND date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalExpense(final long startTime, final long endTime,
      final Continuation<? super Float> $completion) {
    final String _sql = "SELECT SUM(amount) FROM transactions WHERE type = 'EXPENSE' AND date >= ? AND date <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Float>() {
      @Override
      @Nullable
      public Float call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Float _result;
          if (_cursor.moveToFirst()) {
            final Float _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getFloat(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
