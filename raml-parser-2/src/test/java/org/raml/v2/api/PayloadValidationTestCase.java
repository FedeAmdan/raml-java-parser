/*
 * Copyright 2013 (c) MuleSoft, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package org.raml.v2.api;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;
import org.raml.v2.api.model.common.ValidationResult;
import org.raml.v2.api.model.v10.api.Api;
import org.raml.v2.api.model.v10.datamodel.TypeDeclaration;
import org.raml.v2.api.model.v10.methods.Method;

public class PayloadValidationTestCase
{
    @Test
    public void validateFloatPayload()
    {
        File input = new File("src/test/resources/org/raml/v2/api/v10/validation/float.raml");
        assertTrue(input.isFile());
        RamlModelResult ramlModelResult = new RamlModelBuilder().buildApi(input);
        Api api = ramlModelResult.getApiV10();
        Method post = api.resources().get(0).methods().get(0);
        TypeDeclaration appJson = post.body().get(0);
        assertThat(appJson.name(), is("application/json"));
        String jsonExample = "{ \"PriceFloat\": 1.5 }";
        List<ValidationResult> validationResults = appJson.validate(jsonExample);
        assertEquals(0, validationResults.size());
    }
}
