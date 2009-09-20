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
package com.example.dao;


import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.directory.shared.ldap.util.GeneralizedTime;

import com.example.CompanyCar;


public class CompanyCarDao
{

    public void save( CompanyCar car ) throws Exception
    {
        DirContext ctx = new InitialDirContext();

        Attributes attributes = new BasicAttributes( true );
        Attribute objectClass = new BasicAttribute( "objectclass" );
        objectClass.add( "top" );
        objectClass.add( "companyCar" );
        attributes.put( objectClass );
        attributes.put( "licenseNumber", car.getLicenseNumber() );
        attributes.put( "leasingStart", new GeneralizedTime( car.getLeasingStart() ).toGeneralizedTime() );
        attributes.put( "leasingEnd", new GeneralizedTime( car.getLeasingEnd() ).toGeneralizedTime() );

        ctx.bind( "licenseNumber=" + car.getLicenseNumber() + ",ou=Cars,dc=example,dc=com", null, attributes );

        ctx.close();
    }

}
