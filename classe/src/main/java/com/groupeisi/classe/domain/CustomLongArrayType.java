package com.groupeisi.classe.domain;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;

public class CustomLongArrayType implements UserType<Long[]> {
    @Override
    public int getSqlType() {
        return Types.ARRAY;
    }

    @Override
    public Class<Long[]> returnedClass() {
        return Long[].class;
    }

    @Override
    public boolean equals(Long[] longs, Long[] j1) {
        return false;
    }

    @Override
    public int hashCode(Long[] longs) {
        return Arrays.hashCode((Long[]) longs);
    }

    @Override
    public Long[] nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        Array array = resultSet.getArray(i);
        return array != null ? (Long[]) array.getArray() : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Long[] value, int index, SharedSessionContractImplementor session) throws SQLException {
        if (st != null) {
            if (value != null) {
                Array array = session.getJdbcConnectionAccess().obtainConnection().createArrayOf("long", value);
                st.setArray(index, array);
            } else {
                st.setNull(index, Types.ARRAY);
            }
        }
    }

    @Override
    public Long[] deepCopy(Long[] value) {
        return value != null ? ((Long[]) value).clone() : null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Long[] longs) {
        return null;
    }

    @Override
    public Long[] assemble(Serializable serializable, Object o) {
        return new Long[0];
    }
}
