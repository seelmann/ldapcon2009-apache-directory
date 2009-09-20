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


import java.util.Calendar;


public class CompanyCar
{
    private String licenseNumber;

    private Calendar leasingStart;

    private Calendar leasingEnd;

    private User owner;


    public CompanyCar( String licenseNumber, Calendar leasingStart, Calendar leasingEnd )
    {
        this.licenseNumber = licenseNumber;
        this.leasingStart = leasingStart;
        this.leasingEnd = leasingEnd;
    }


    public String getLicenseNumber()
    {
        return licenseNumber;
    }


    public void setLicenseNumber( String licenseNumber )
    {
        this.licenseNumber = licenseNumber;
    }


    public Calendar getLeasingStart()
    {
        return leasingStart;
    }


    public void setLeasingStart( Calendar leasingStart )
    {
        this.leasingStart = leasingStart;
    }


    public Calendar getLeasingEnd()
    {
        return leasingEnd;
    }


    public void setLeasingEnd( Calendar leasingEnd )
    {
        this.leasingEnd = leasingEnd;
    }


    public User getOwner()
    {
        return owner;
    }


    public void setOwner( User owner )
    {
        this.owner = owner;
    }


    @Override
    public String toString()
    {
        return "CompanyCar [leasingEnd=" + leasingEnd + ", leasingStart=" + leasingStart + ", licenseNumber="
            + licenseNumber + ", owner=" + owner + "]";
    }

}
