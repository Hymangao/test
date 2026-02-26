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
import com.example.sublandlord.data.local.entity.ContractStatus;
import com.example.sublandlord.data.local.entity.LateFeePolicy;
import com.example.sublandlord.data.local.entity.LeaseEntity;
import com.example.sublandlord.data.local.entity.PaymentFrequency;
import com.example.sublandlord.data.local.entity.PaymentMethod;
import com.example.sublandlord.data.local.entity.RenewalRecord;
import com.example.sublandlord.data.local.entity.UtilitiesPolicy;
import java.lang.Class;
import java.lang.Exception;
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
public final class LeaseDao_Impl implements LeaseDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LeaseEntity> __insertionAdapterOfLeaseEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<LeaseEntity> __deletionAdapterOfLeaseEntity;

  private final EntityDeletionOrUpdateAdapter<LeaseEntity> __updateAdapterOfLeaseEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLeaseById;

  private final SharedSQLiteStatement __preparedStmtOfUpdateExpiredLeases;

  public LeaseDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLeaseEntity = new EntityInsertionAdapter<LeaseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `leases` (`id`,`roomId`,`tenantId`,`startDate`,`endDate`,`monthlyRent`,`deposit`,`paymentFrequency`,`paymentDay`,`paymentMethod`,`utilities`,`lateFee`,`status`,`contractUrl`,`signedDate`,`renewalHistory`,`notes`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaseEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRoomId());
        statement.bindString(3, entity.getTenantId());
        statement.bindLong(4, entity.getStartDate());
        statement.bindLong(5, entity.getEndDate());
        statement.bindDouble(6, entity.getMonthlyRent());
        statement.bindDouble(7, entity.getDeposit());
        final String _tmp = __converters.fromPaymentFrequency(entity.getPaymentFrequency());
        statement.bindString(8, _tmp);
        statement.bindLong(9, entity.getPaymentDay());
        final String _tmp_1 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(10, _tmp_1);
        final String _tmp_2 = __converters.fromUtilitiesPolicy(entity.getUtilities());
        statement.bindString(11, _tmp_2);
        final String _tmp_3 = __converters.fromLateFeePolicy(entity.getLateFee());
        if (_tmp_3 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_3);
        }
        final String _tmp_4 = __converters.fromContractStatus(entity.getStatus());
        statement.bindString(13, _tmp_4);
        if (entity.getContractUrl() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getContractUrl());
        }
        if (entity.getSignedDate() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getSignedDate());
        }
        final String _tmp_5 = __converters.fromRenewalRecordList(entity.getRenewalHistory());
        statement.bindString(16, _tmp_5);
        if (entity.getNotes() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getNotes());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfLeaseEntity = new EntityDeletionOrUpdateAdapter<LeaseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `leases` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaseEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfLeaseEntity = new EntityDeletionOrUpdateAdapter<LeaseEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `leases` SET `id` = ?,`roomId` = ?,`tenantId` = ?,`startDate` = ?,`endDate` = ?,`monthlyRent` = ?,`deposit` = ?,`paymentFrequency` = ?,`paymentDay` = ?,`paymentMethod` = ?,`utilities` = ?,`lateFee` = ?,`status` = ?,`contractUrl` = ?,`signedDate` = ?,`renewalHistory` = ?,`notes` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeaseEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getRoomId());
        statement.bindString(3, entity.getTenantId());
        statement.bindLong(4, entity.getStartDate());
        statement.bindLong(5, entity.getEndDate());
        statement.bindDouble(6, entity.getMonthlyRent());
        statement.bindDouble(7, entity.getDeposit());
        final String _tmp = __converters.fromPaymentFrequency(entity.getPaymentFrequency());
        statement.bindString(8, _tmp);
        statement.bindLong(9, entity.getPaymentDay());
        final String _tmp_1 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(10, _tmp_1);
        final String _tmp_2 = __converters.fromUtilitiesPolicy(entity.getUtilities());
        statement.bindString(11, _tmp_2);
        final String _tmp_3 = __converters.fromLateFeePolicy(entity.getLateFee());
        if (_tmp_3 == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, _tmp_3);
        }
        final String _tmp_4 = __converters.fromContractStatus(entity.getStatus());
        statement.bindString(13, _tmp_4);
        if (entity.getContractUrl() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getContractUrl());
        }
        if (entity.getSignedDate() == null) {
          statement.bindNull(15);
        } else {
          statement.bindLong(15, entity.getSignedDate());
        }
        final String _tmp_5 = __converters.fromRenewalRecordList(entity.getRenewalHistory());
        statement.bindString(16, _tmp_5);
        if (entity.getNotes() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getNotes());
        }
        statement.bindLong(18, entity.getCreatedAt());
        statement.bindLong(19, entity.getUpdatedAt());
        statement.bindString(20, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteLeaseById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM leases WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateExpiredLeases = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE leases SET status = 'EXPIRED' WHERE endDate < ? AND status = 'ACTIVE'";
        return _query;
      }
    };
  }

  @Override
  public Object insertLease(final LeaseEntity lease, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLeaseEntity.insert(lease);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLeases(final List<LeaseEntity> leases,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLeaseEntity.insert(leases);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLease(final LeaseEntity lease, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLeaseEntity.handle(lease);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLease(final LeaseEntity lease, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLeaseEntity.handle(lease);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLeaseById(final String leaseId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLeaseById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, leaseId);
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
          __preparedStmtOfDeleteLeaseById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateExpiredLeases(final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateExpiredLeases.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
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
          __preparedStmtOfUpdateExpiredLeases.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LeaseEntity>> getAllLeases() {
    final String _sql = "SELECT * FROM leases ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<List<LeaseEntity>>() {
      @Override
      @NonNull
      public List<LeaseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LeaseEntity> _result = new ArrayList<LeaseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaseEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _item = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<LeaseEntity> getLeaseById(final String leaseId) {
    final String _sql = "SELECT * FROM leases WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, leaseId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<LeaseEntity>() {
      @Override
      @Nullable
      public LeaseEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final LeaseEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _result = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LeaseEntity>> getLeasesByRoomId(final String roomId) {
    final String _sql = "SELECT * FROM leases WHERE roomId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<List<LeaseEntity>>() {
      @Override
      @NonNull
      public List<LeaseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LeaseEntity> _result = new ArrayList<LeaseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaseEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _item = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LeaseEntity>> getLeasesByTenantId(final String tenantId) {
    final String _sql = "SELECT * FROM leases WHERE tenantId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, tenantId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<List<LeaseEntity>>() {
      @Override
      @NonNull
      public List<LeaseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LeaseEntity> _result = new ArrayList<LeaseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaseEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _item = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LeaseEntity>> getLeasesByStatus(final ContractStatus status) {
    final String _sql = "SELECT * FROM leases WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromContractStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<List<LeaseEntity>>() {
      @Override
      @NonNull
      public List<LeaseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LeaseEntity> _result = new ArrayList<LeaseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaseEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp_1);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_2);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_3);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_4 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_4);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_5);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_6;
            _tmp_6 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_6);
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
            _item = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LeaseEntity>> getExpiringLeases(final long timestamp) {
    final String _sql = "SELECT * FROM leases WHERE endDate <= ? AND status = 'ACTIVE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<List<LeaseEntity>>() {
      @Override
      @NonNull
      public List<LeaseEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LeaseEntity> _result = new ArrayList<LeaseEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeaseEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _item = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<LeaseEntity> getActiveLeaseByRoomId(final String roomId) {
    final String _sql = "SELECT * FROM leases WHERE roomId = ? AND status = 'ACTIVE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, roomId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leases"}, new Callable<LeaseEntity>() {
      @Override
      @Nullable
      public LeaseEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfTenantId = CursorUtil.getColumnIndexOrThrow(_cursor, "tenantId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfUtilities = CursorUtil.getColumnIndexOrThrow(_cursor, "utilities");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final LeaseEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpRoomId;
            _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            final String _tmpTenantId;
            _tmpTenantId = _cursor.getString(_cursorIndexOfTenantId);
            final long _tmpStartDate;
            _tmpStartDate = _cursor.getLong(_cursorIndexOfStartDate);
            final long _tmpEndDate;
            _tmpEndDate = _cursor.getLong(_cursorIndexOfEndDate);
            final float _tmpMonthlyRent;
            _tmpMonthlyRent = _cursor.getFloat(_cursorIndexOfMonthlyRent);
            final float _tmpDeposit;
            _tmpDeposit = _cursor.getFloat(_cursorIndexOfDeposit);
            final PaymentFrequency _tmpPaymentFrequency;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfPaymentFrequency);
            _tmpPaymentFrequency = __converters.toPaymentFrequency(_tmp);
            final int _tmpPaymentDay;
            _tmpPaymentDay = _cursor.getInt(_cursorIndexOfPaymentDay);
            final PaymentMethod _tmpPaymentMethod;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfPaymentMethod);
            _tmpPaymentMethod = __converters.toPaymentMethod(_tmp_1);
            final UtilitiesPolicy _tmpUtilities;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfUtilities);
            _tmpUtilities = __converters.toUtilitiesPolicy(_tmp_2);
            final LateFeePolicy _tmpLateFee;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_3 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_3);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_4);
            final String _tmpContractUrl;
            if (_cursor.isNull(_cursorIndexOfContractUrl)) {
              _tmpContractUrl = null;
            } else {
              _tmpContractUrl = _cursor.getString(_cursorIndexOfContractUrl);
            }
            final Long _tmpSignedDate;
            if (_cursor.isNull(_cursorIndexOfSignedDate)) {
              _tmpSignedDate = null;
            } else {
              _tmpSignedDate = _cursor.getLong(_cursorIndexOfSignedDate);
            }
            final List<RenewalRecord> _tmpRenewalHistory;
            final String _tmp_5;
            _tmp_5 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_5);
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
            _result = new LeaseEntity(_tmpId,_tmpRoomId,_tmpTenantId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpUtilities,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
