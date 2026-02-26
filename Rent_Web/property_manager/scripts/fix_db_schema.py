import sqlite3, os

# Run from project root: python scripts/fix_db_schema.py

dbs=['instance/property_manager.db','dist/instance/property_manager.db']
for db in dbs:
    print('Processing', db)
    if not os.path.exists(db):
        print('  Not found')
        continue
    try:
        con=sqlite3.connect(db)
        cur=con.cursor()
    except Exception as e:
        print('  Could not open DB:', e)
        continue
    def has_col(table,col):
        try:
            cur.execute(f"PRAGMA table_info({table})")
            cols=[r[1] for r in cur.fetchall()]
            return col in cols
        except Exception as e:
            print('  PRAGMA error:', e)
            return False
    changes=0
    try:
        if not has_col('tenant_contract','payment_method'):
            cur.execute("ALTER TABLE tenant_contract ADD COLUMN payment_method TEXT DEFAULT ''")
            print('  Added tenant_contract.payment_method')
            changes+=1
        else:
            print('  tenant_contract.payment_method exists')
        if not has_col('tenant_contract','attachments'):
            cur.execute("ALTER TABLE tenant_contract ADD COLUMN attachments TEXT DEFAULT ''")
            print('  Added tenant_contract.attachments')
            changes+=1
        else:
            print('  tenant_contract.attachments exists')
        if not has_col('tenant_contract','notes'):
            cur.execute("ALTER TABLE tenant_contract ADD COLUMN notes TEXT DEFAULT ''")
            print('  Added tenant_contract.notes')
            changes+=1
        else:
            print('  tenant_contract.notes exists')
        if not has_col('landlord_contract','payment_method'):
            cur.execute("ALTER TABLE landlord_contract ADD COLUMN payment_method TEXT DEFAULT ''")
            print('  Added landlord_contract.payment_method')
            changes+=1
        else:
            print('  landlord_contract.payment_method exists')
        if not has_col('landlord_contract','attachments'):
            cur.execute("ALTER TABLE landlord_contract ADD COLUMN attachments TEXT DEFAULT ''")
            print('  Added landlord_contract.attachments')
            changes+=1
        else:
            print('  landlord_contract.attachments exists')
        if not has_col('landlord_contract','notes'):
            cur.execute("ALTER TABLE landlord_contract ADD COLUMN notes TEXT DEFAULT ''")
            print('  Added landlord_contract.notes')
            changes+=1
        else:
            print('  landlord_contract.notes exists')
        con.commit()
        print('  Done. Changes:', changes)
    except Exception as e:
        print('  Error while altering:', e)
    finally:
        con.close()
