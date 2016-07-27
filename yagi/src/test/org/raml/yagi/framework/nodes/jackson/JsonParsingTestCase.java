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
package org.raml.yagi.framework.nodes.jackson;

import static org.junit.Assert.assertEquals;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;

public class JsonParsingTestCase
{
    @Test
    public void getNotNullFloatValue() throws IOException
    {
        Reader reader = new StringReader("{ \"PriceFloat\": 1.5 }");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(reader, JsonNode.class);
        String actualValue = new JFloatingNode(((NumericNode)rootNode.fields().next().getValue()),"", null).getValue().toString();
        assertEquals("1.5", actualValue);
    }

    @Test
    public void getNotNullBooleanValue() throws IOException
    {
        Reader reader = new StringReader("{ \"boolean\": true }");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(reader, JsonNode.class);
        String actualValue = new JBooleanNode(((BooleanNode)rootNode.fields().next().getValue()), "", null).getValue().toString();
        assertEquals("true", actualValue);
    }

    @Test
    public void getNotNullInt32Value() throws IOException
    {
        Reader reader = new StringReader("{ \"int\": 333 }");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(reader, JsonNode.class);
        String actualValue = new JIntegerNode(((IntNode) rootNode.fields().next().getValue()), "", null).getValue().toString();
        assertEquals("333", actualValue);
    }

    @Test
    public void getNotNullStringValue() throws IOException
    {
        Reader reader = new StringReader("{ \"string\": \"value\" }");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(reader, JsonNode.class);
        String actualValue = new JStringNode(((TextNode) rootNode.fields().next().getValue()), "", null).getValue().toString();
        assertEquals("value", actualValue);
    }
}
