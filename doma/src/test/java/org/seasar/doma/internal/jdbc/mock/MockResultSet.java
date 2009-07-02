/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.internal.jdbc.mock;

import static org.seasar.doma.internal.util.Assertions.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.seasar.doma.internal.util.Assertions;


/**
 * 
 * @author taedium
 * 
 */
public class MockResultSet extends MockWrapper implements ResultSet {

    public MockResultSetMetaData metaData = new MockResultSetMetaData();

    public List<RowData> rows = new ArrayList<RowData>();

    public boolean closed;

    protected int rowIndex = -1;

    public MockResultSet() {
    }

    public MockResultSet(MockResultSetMetaData metaData) {
        this.metaData = metaData;
    }

    protected RowData getRowData() {
        assertTrue(!closed);
        return rows.get(rowIndex);
    }

    @Override
    public boolean absolute(int row) throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public void afterLast() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void beforeFirst() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void clearWarnings() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void close() throws SQLException {
        closed = true;
    }

    @Override
    public void deleteRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public int findColumn(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public boolean first() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public Array getArray(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Array getArray(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getBigDecimal(int columnIndex, int scale)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        assertTrue(!closed);
        Object value = getObject(columnIndex);
        if (value == null) {
            return null;
        }
        return BigDecimal.class.cast(value);
    }

    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal getBigDecimal(String columnLabel, int scale)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Blob getBlob(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Blob getBlob(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public boolean getBoolean(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean getBoolean(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public byte getByte(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public byte getByte(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public byte[] getBytes(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public byte[] getBytes(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Reader getCharacterStream(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Reader getCharacterStream(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Clob getClob(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Clob getClob(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public int getConcurrency() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public String getCursorName() throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Date getDate(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Date getDate(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public double getDouble(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public double getDouble(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public int getFetchDirection() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public int getFetchSize() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public float getFloat(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public float getFloat(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public int getHoldability() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public int getInt(int columnIndex) throws SQLException {
        assertTrue(!closed);
        Object value = getObject(columnIndex);
        if (value == null) {
            return 0;
        }
        return Integer.class.cast(value);
    }

    @Override
    public int getInt(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public long getLong(int columnIndex) throws SQLException {
        assertTrue(!closed);
        Object value = getObject(columnIndex);
        if (value == null) {
            return 0;
        }
        return Long.class.cast(value);
    }

    @Override
    public long getLong(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        assertTrue(!closed);
        if (metaData == null) {
            metaData = new MockResultSetMetaData();
        }
        return metaData;
    }

    @Override
    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public NClob getNClob(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public NClob getNClob(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public String getNString(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public String getNString(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Object getObject(int columnIndex, Map<String, Class<?>> map)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Object getObject(int columnIndex) throws SQLException {
        assertTrue(!closed);
        return getRowData().get(columnIndex);
    }

    @Override
    public Object getObject(String columnLabel, Map<String, Class<?>> map)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Object getObject(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Ref getRef(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Ref getRef(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public int getRow() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public RowId getRowId(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public RowId getRowId(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public short getShort(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public short getShort(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @Override
    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Statement getStatement() throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public String getString(int columnIndex) throws SQLException {
        Object value = getObject(columnIndex);
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    @Override
    public String getString(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Time getTime(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Time getTime(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Timestamp getTimestamp(int columnIndex, Calendar cal)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Timestamp getTimestamp(String columnLabel, Calendar cal)
            throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public int getType() throws SQLException {
        Assertions.notYetImplemented();
        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public URL getURL(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public URL getURL(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        Assertions.notYetImplemented();
        return null;
    }

    @Override
    public void insertRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public boolean isAfterLast() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean isBeforeFirst() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean isClosed() throws SQLException {
        return closed;
    }

    @Override
    public boolean isFirst() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean isLast() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean last() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void moveToInsertRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public boolean next() throws SQLException {
        assertTrue(!closed);
        rowIndex++;
        return rowIndex < rows.size();
    }

    @Override
    public boolean previous() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public void refreshRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public boolean relative(int rows) throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean rowDeleted() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean rowInserted() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public boolean rowUpdated() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateArray(int columnIndex, Array x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateArray(String columnLabel, Array x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(int columnIndex, InputStream x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, int length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateAsciiStream(String columnLabel, InputStream x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBigDecimal(int columnIndex, BigDecimal x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBigDecimal(String columnLabel, BigDecimal x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, int length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(int columnIndex, InputStream x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x, int length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x,
            long length) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBinaryStream(String columnLabel, InputStream x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(int columnIndex, InputStream inputStream)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream,
            long length) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBlob(String columnLabel, InputStream inputStream)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBoolean(String columnLabel, boolean x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateByte(int columnIndex, byte x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateByte(String columnLabel, byte x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, int length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader,
            int length) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(int columnIndex, Clob x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(String columnLabel, Clob x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateClob(String columnLabel, Reader reader)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateDate(int columnIndex, Date x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateDate(String columnLabel, Date x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateDouble(int columnIndex, double x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateDouble(String columnLabel, double x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateFloat(int columnIndex, float x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateFloat(String columnLabel, float x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateInt(int columnIndex, int x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateInt(String columnLabel, int x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateLong(int columnIndex, long x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateLong(String columnLabel, long x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNCharacterStream(int columnIndex, Reader x)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader,
            long length) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNCharacterStream(String columnLabel, Reader reader)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(int columnIndex, NClob clob) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(String columnLabel, NClob clob) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader, long length)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNClob(String columnLabel, Reader reader)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNString(int columnIndex, String string)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNString(String columnLabel, String string)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNull(int columnIndex) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateNull(String columnLabel) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateObject(int columnIndex, Object x, int scaleOrLength)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateObject(int columnIndex, Object x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateObject(String columnLabel, Object x, int scaleOrLength)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateObject(String columnLabel, Object x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateRef(int columnIndex, Ref x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateRef(String columnLabel, Ref x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateRow() throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateShort(int columnIndex, short x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateShort(String columnLabel, short x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateSQLXML(int columnIndex, SQLXML xmlObject)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateSQLXML(String columnLabel, SQLXML xmlObject)
            throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateString(int columnIndex, String x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateString(String columnLabel, String x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateTime(int columnIndex, Time x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateTime(String columnLabel, Time x) throws SQLException {
        Assertions.notYetImplemented();

    }

    @Override
    public void updateTimestamp(int columnIndex, Timestamp x)
            throws SQLException {
        Assertions.notYetImplemented();
    }

    @Override
    public void updateTimestamp(String columnLabel, Timestamp x)
            throws SQLException {
        Assertions.notYetImplemented();
    }

    @Override
    public boolean wasNull() throws SQLException {
        Assertions.notYetImplemented();
        return false;
    }

}