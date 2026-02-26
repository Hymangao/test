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
import com.example.sublandlord.data.local.entity.LandlordContractEntity;
import com.example.sublandlord.data.local.entity.LateFeePolicy;
import com.example.sublandlord.data.local.entity.PaymentFrequency;
import com.example.sublandlord.data.local.entity.PaymentMethod;
import com.example.sublandlord.data.local.entity.RenewalRecord;
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
public final class LandlordContractDao_Impl implements LandlordContractDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LandlordContractEntity> __insertionAdapterOfLandlordContractEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<LandlordContractEntity> __deletionAdapterOfLandlordContractEntity;

  private final EntityDeletionOrUpdateAdapter<LandlordContractEntity> __updateAdapterOfLandlordContractEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteContractById;

  public LandlordContractDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLandlordContractEntity = new EntityInsertionAdapter<LandlordContractEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `landlord_contracts` (`id`,`propertyId`,`landlordId`,`startDate`,`endDate`,`monthlyRent`,`deposit`,`paymentFrequency`,`paymentDay`,`paymentMethod`,`lateFee`,`status`,`contractUrl`,`signedDate`,`renewalHistory`,`notes`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordContractEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getPropertyId());
        statement.bindString(3, entity.getLandlordId());
        statement.bindLong(4, entity.getStartDate());
        statement.bindLong(5, entity.getEndDate());
        statement.bindDouble(6, entity.getMonthlyRent());
        statement.bindDouble(7, entity.getDeposit());
        final String _tmp = __converters.fromPaymentFrequency(entity.getPaymentFrequency());
        statement.bindString(8, _tmp);
        statement.bindLong(9, entity.getPaymentDay());
        final String _tmp_1 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(10, _tmp_1);
        final String _tmp_2 = __converters.fromLateFeePolicy(entity.getLateFee());
        if (_tmp_2 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp_2);
        }
        final String _tmp_3 = __converters.fromContractStatus(entity.getStatus());
        statement.bindString(12, _tmp_3);
        if (entity.getContractUrl() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getContractUrl());
        }
        if (entity.getSignedDate() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getSignedDate());
        }
        final String _tmp_4 = __converters.fromRenewalRecordList(entity.getRenewalHistory());
        statement.bindString(15, _tmp_4);
        if (entity.getNotes() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotes());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfLandlordContractEntity = new EntityDeletionOrUpdateAdapter<LandlordContractEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `landlord_contracts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordContractEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfLandlordContractEntity = new EntityDeletionOrUpdateAdapter<LandlordContractEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `landlord_contracts` SET `id` = ?,`propertyId` = ?,`landlordId` = ?,`startDate` = ?,`endDate` = ?,`monthlyRent` = ?,`deposit` = ?,`paymentFrequency` = ?,`paymentDay` = ?,`paymentMethod` = ?,`lateFee` = ?,`status` = ?,`contractUrl` = ?,`signedDate` = ?,`renewalHistory` = ?,`notes` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LandlordContractEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getPropertyId());
        statement.bindString(3, entity.getLandlordId());
        statement.bindLong(4, entity.getStartDate());
        statement.bindLong(5, entity.getEndDate());
        statement.bindDouble(6, entity.getMonthlyRent());
        statement.bindDouble(7, entity.getDeposit());
        final String _tmp = __converters.fromPaymentFrequency(entity.getPaymentFrequency());
        statement.bindString(8, _tmp);
        statement.bindLong(9, entity.getPaymentDay());
        final String _tmp_1 = __converters.fromPaymentMethod(entity.getPaymentMethod());
        statement.bindString(10, _tmp_1);
        final String _tmp_2 = __converters.fromLateFeePolicy(entity.getLateFee());
        if (_tmp_2 == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, _tmp_2);
        }
        final String _tmp_3 = __converters.fromContractStatus(entity.getStatus());
        statement.bindString(12, _tmp_3);
        if (entity.getContractUrl() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getContractUrl());
        }
        if (entity.getSignedDate() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getSignedDate());
        }
        final String _tmp_4 = __converters.fromRenewalRecordList(entity.getRenewalHistory());
        statement.bindString(15, _tmp_4);
        if (entity.getNotes() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getNotes());
        }
        statement.bindLong(17, entity.getCreatedAt());
        statement.bindLong(18, entity.getUpdatedAt());
        statement.bindString(19, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteContractById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM landlord_contracts WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertContract(final LandlordContractEntity contract,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLandlordContractEntity.insert(contract);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertContracts(final List<LandlordContractEntity> contracts,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfLandlordContractEntity.insert(contracts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteContract(final LandlordContractEntity contract,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfLandlordContractEntity.handle(contract);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateContract(final LandlordContractEntity contract,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfLandlordContractEntity.handle(contract);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteContractById(final String contractId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteContractById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, contractId);
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
          __preparedStmtOfDeleteContractById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LandlordContractEntity>> getAllContracts() {
    final String _sql = "SELECT * FROM landlord_contracts ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<List<LandlordContractEntity>>() {
      @Override
      @NonNull
      public List<LandlordContractEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordContractEntity> _result = new ArrayList<LandlordContractEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordContractEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            final LateFeePolicy _tmpLateFee;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_2 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_2);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_3);
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
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_4);
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
            _item = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<LandlordContractEntity> getContractById(final String contractId) {
    final String _sql = "SELECT * FROM landlord_contracts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, contractId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<LandlordContractEntity>() {
      @Override
      @Nullable
      public LandlordContractEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final LandlordContractEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            final LateFeePolicy _tmpLateFee;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_2 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_2);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_3);
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
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_4);
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
            _result = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LandlordContractEntity>> getContractsByPropertyId(final String propertyId) {
    final String _sql = "SELECT * FROM landlord_contracts WHERE propertyId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, propertyId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<List<LandlordContractEntity>>() {
      @Override
      @NonNull
      public List<LandlordContractEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordContractEntity> _result = new ArrayList<LandlordContractEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordContractEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            final LateFeePolicy _tmpLateFee;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_2 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_2);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_3);
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
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_4);
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
            _item = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LandlordContractEntity>> getContractsByLandlordId(final String landlordId) {
    final String _sql = "SELECT * FROM landlord_contracts WHERE landlordId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, landlordId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<List<LandlordContractEntity>>() {
      @Override
      @NonNull
      public List<LandlordContractEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordContractEntity> _result = new ArrayList<LandlordContractEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordContractEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            final LateFeePolicy _tmpLateFee;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_2 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_2);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_3);
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
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_4);
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
            _item = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LandlordContractEntity>> getContractsByStatus(final ContractStatus status) {
    final String _sql = "SELECT * FROM landlord_contracts WHERE status = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __converters.fromContractStatus(status);
    _statement.bindString(_argIndex, _tmp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<List<LandlordContractEntity>>() {
      @Override
      @NonNull
      public List<LandlordContractEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordContractEntity> _result = new ArrayList<LandlordContractEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordContractEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            _item = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<LandlordContractEntity>> getExpiringContracts(final long timestamp) {
    final String _sql = "SELECT * FROM landlord_contracts WHERE endDate <= ? AND status = 'ACTIVE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, timestamp);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"landlord_contracts"}, new Callable<List<LandlordContractEntity>>() {
      @Override
      @NonNull
      public List<LandlordContractEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfPropertyId = CursorUtil.getColumnIndexOrThrow(_cursor, "propertyId");
          final int _cursorIndexOfLandlordId = CursorUtil.getColumnIndexOrThrow(_cursor, "landlordId");
          final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "startDate");
          final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "endDate");
          final int _cursorIndexOfMonthlyRent = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyRent");
          final int _cursorIndexOfDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "deposit");
          final int _cursorIndexOfPaymentFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentFrequency");
          final int _cursorIndexOfPaymentDay = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDay");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final int _cursorIndexOfLateFee = CursorUtil.getColumnIndexOrThrow(_cursor, "lateFee");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfContractUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "contractUrl");
          final int _cursorIndexOfSignedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "signedDate");
          final int _cursorIndexOfRenewalHistory = CursorUtil.getColumnIndexOrThrow(_cursor, "renewalHistory");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<LandlordContractEntity> _result = new ArrayList<LandlordContractEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LandlordContractEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpPropertyId;
            _tmpPropertyId = _cursor.getString(_cursorIndexOfPropertyId);
            final String _tmpLandlordId;
            _tmpLandlordId = _cursor.getString(_cursorIndexOfLandlordId);
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
            final LateFeePolicy _tmpLateFee;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfLateFee)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfLateFee);
            }
            if (_tmp_2 == null) {
              _tmpLateFee = null;
            } else {
              _tmpLateFee = __converters.toLateFeePolicy(_tmp_2);
            }
            final ContractStatus _tmpStatus;
            final String _tmp_3;
            _tmp_3 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toContractStatus(_tmp_3);
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
            final String _tmp_4;
            _tmp_4 = _cursor.getString(_cursorIndexOfRenewalHistory);
            _tmpRenewalHistory = __converters.toRenewalRecordList(_tmp_4);
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
            _item = new LandlordContractEntity(_tmpId,_tmpPropertyId,_tmpLandlordId,_tmpStartDate,_tmpEndDate,_tmpMonthlyRent,_tmpDeposit,_tmpPaymentFrequency,_tmpPaymentDay,_tmpPaymentMethod,_tmpLateFee,_tmpStatus,_tmpContractUrl,_tmpSignedDate,_tmpRenewalHistory,_tmpNotes,_tmpCreatedAt,_tmpUpdatedAt);
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
