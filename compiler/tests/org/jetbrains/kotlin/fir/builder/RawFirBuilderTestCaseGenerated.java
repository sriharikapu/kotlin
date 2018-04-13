/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.builder;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/fir/rawBuilder")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class RawFirBuilderTestCaseGenerated extends AbstractRawFirBuilderTestCase {
    public void testAllFilesPresentInRawBuilder() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/fir/rawBuilder"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("compiler/testData/fir/rawBuilder/declarations")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class Declarations extends AbstractRawFirBuilderTestCase {
        public void testAllFilesPresentInDeclarations() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/fir/rawBuilder/declarations"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
        }

        @TestMetadata("complexTypes.kt")
        public void testComplexTypes() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/complexTypes.kt");
            doRawFirTest(fileName);
        }

        @TestMetadata("enums.kt")
        public void testEnums() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/enums.kt");
            doRawFirTest(fileName);
        }

        @TestMetadata("noPrimaryConstructor.kt")
        public void testNoPrimaryConstructor() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/noPrimaryConstructor.kt");
            doRawFirTest(fileName);
        }

        @TestMetadata("simpleClass.kt")
        public void testSimpleClass() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/simpleClass.kt");
            doRawFirTest(fileName);
        }

        @TestMetadata("simpleFun.kt")
        public void testSimpleFun() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/simpleFun.kt");
            doRawFirTest(fileName);
        }

        @TestMetadata("typeParameters.kt")
        public void testTypeParameters() throws Exception {
            String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/fir/rawBuilder/declarations/typeParameters.kt");
            doRawFirTest(fileName);
        }
    }
}