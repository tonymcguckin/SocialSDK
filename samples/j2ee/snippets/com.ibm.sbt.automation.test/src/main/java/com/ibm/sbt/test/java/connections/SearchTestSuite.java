/*
 * © Copyright IBM Corp. 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */
package com.ibm.sbt.test.java.connections;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.sbt.automation.core.environment.TestEnvironment;
import com.ibm.sbt.test.java.connections.search.BlogConstraint;
import com.ibm.sbt.test.java.connections.search.CommunityFullText;
import com.ibm.sbt.test.java.connections.search.CommunityTagSearch;
import com.ibm.sbt.test.java.connections.search.GetPeople;
import com.ibm.sbt.test.java.connections.search.GetResults;
import com.ibm.sbt.test.java.connections.search.GetResultsByTag;
import com.ibm.sbt.test.java.connections.search.GetScopes;

/**
 * @author mkataria
 * 
 * @since Nov 14 2013
 */
@RunWith(Suite.class)
@SuiteClasses({ BlogConstraint.class, CommunityFullText.class, CommunityTagSearch.class, GetPeople.class, GetResults.class, GetResultsByTag.class,
        GetScopes.class }) 
public class SearchTestSuite {
    @AfterClass
    public static void cleanup() {
        TestEnvironment.cleanup();
    }
}
