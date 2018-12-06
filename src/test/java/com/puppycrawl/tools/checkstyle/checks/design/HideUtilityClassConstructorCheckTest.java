////////////////////////////////////////////////////////////////////////////////
// checkstyle: Checks Java source code for adherence to a set of rules.
// Copyright (C) 2001-2018 the original author or authors.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
////////////////////////////////////////////////////////////////////////////////

package com.puppycrawl.tools.checkstyle.checks.design;

import static com.puppycrawl.tools.checkstyle.checks.design.HideUtilityClassConstructorCheck.MSG_KEY;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class HideUtilityClassConstructorCheckTest
    extends AbstractModuleTestSupport {

    @Override
    protected String getPackageLocation() {
        return "com/puppycrawl/tools/checkstyle/checks/design/hideutilityclassconstructor";
    }

    @Test
    public void testGetRequiredTokens() {
        final HideUtilityClassConstructorCheck checkObj =
            new HideUtilityClassConstructorCheck();
        final int[] expected = {TokenTypes.CLASS_DEF};
        assertArrayEquals("Default required tokens are invalid",
            expected, checkObj.getRequiredTokens());
    }

    @Test
    public void testUtilClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = {
            "3:1: " + getCheckMessage(MSG_KEY),
        };
        verify(checkConfig, getPath("InputHideUtilityClassConstructorInnerStaticClasses.java"),
                expected);
    }

    @Test
    public void testUtilClassPublicCtor() throws Exception {
        final DefaultConfiguration checkConfig =
                createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = {
            "3:1: " + getCheckMessage(MSG_KEY),
        };
        verify(checkConfig, getPath("InputHideUtilityClassConstructorPublic.java"), expected);
    }

    @Test
    public void testUtilClassPrivateCtor() throws Exception {
        final DefaultConfiguration checkConfig =
                createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorPrivate.java"), expected);
    }

    /** Non-static methods - always OK. */
    @Test
    public void testNonUtilClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorDesignForExtension.java"),
                expected);
    }

    @Test
    public void testDerivedNonUtilClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorNonUtilityClass.java"),
                expected);
    }

    @Test
    public void testOnlyNonStaticFieldNonUtilClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorRegression.java"),
                expected);
    }

    @Test
    public void testEmptyAbstractClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorAbstractSerializable.java"),
                expected);
    }

    @Test
    public void testEmptyClassWithOnlyPrivateFields() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructorSerializable.java"),
                expected);
    }

    @Test
    public void testClassWithStaticInnerClass() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath(
                "InputHideUtilityClassConstructorSerializableInnerStatic.java"),
                expected);
    }

    @Test
    public void testProtectedCtor() throws Exception {
        final DefaultConfiguration checkConfig =
            createModuleConfig(HideUtilityClassConstructorCheck.class);
        final String[] expected = CommonUtil.EMPTY_STRING_ARRAY;
        verify(checkConfig, getPath("InputHideUtilityClassConstructor.java"), expected);
    }

    @Test
    public void testGetAcceptableTokens() {
        final HideUtilityClassConstructorCheck obj = new HideUtilityClassConstructorCheck();
        final int[] expected = {TokenTypes.CLASS_DEF};
        assertArrayEquals("Default acceptable tokens are invalid",
            expected, obj.getAcceptableTokens());
    }

}
