/**********************************************************************
Copyright (c) 2009 Stefan Seelmann. All rights reserved.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **********************************************************************/
package com.example;


import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;

import org.apache.directory.server.core.integ.Level;
import org.apache.directory.server.core.integ.annotations.ApplyLdifFiles;
import org.apache.directory.server.core.integ.annotations.CleanupLevel;
import org.apache.directory.server.core.integ.annotations.Factory;
import org.apache.directory.server.integ.SiRunner;
import org.apache.directory.server.ldap.LdapServer;
import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.entry.EntryAttribute;
import org.apache.directory.shared.ldap.name.LdapDN;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.example.dao.CompanyCarDao;


@RunWith(SiRunner.class)
@CleanupLevel(Level.METHOD)
@Factory(ApacheDsFactory.class)
@ApplyLdifFiles(
    { "/companyCar_schema.ldif", "/companyCar_data.ldif" })
public class CompanyCarDaoTest
{

    public static LdapServer ldapServer;


    @Test
    public void testCreateWithoutOwner() throws Exception
    {
        // assert the company car doesn't exist
        assertFalse( ldapServer.getDirectoryService().getAdminSession().exists(
            new LdapDN( "licenseNumber=QQ-AA-1234,ou=Cars,dc=example,dc=com" ) ) );

        // create the company car
        CompanyCar car = new CompanyCar( "QQ-AA-1234", Calendar.getInstance(), Calendar.getInstance() );
        CompanyCarDao dao = new CompanyCarDao();
        dao.save( car );

        // assert the company car was created
        Entry entry = ldapServer.getDirectoryService().getAdminSession().lookup(
            new LdapDN( "licenseNumber=QQ-AA-1234,ou=Cars,dc=example,dc=com" ) );
        assertNotNull( entry );
        EntryAttribute licenseNumberAttr = entry.get( "licenseNumber" );
        assertNotNull( licenseNumberAttr );
        assertEquals( 1, licenseNumberAttr.size() );
        assertEquals( "QQ-AA-1234", licenseNumberAttr.get().getString() );
        assertNotNull( entry.get( "leasingStart" ) );
        assertNotNull( entry.get( "leasingEnd" ) );
    }

}
