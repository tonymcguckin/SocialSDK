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

package com.ibm.sbt.services.client.connections.wikis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.ibm.sbt.services.client.base.IFeedHandler;
import com.ibm.sbt.services.client.base.serializers.BaseEntitySerializer.DateSerializer;

/**
 * @author Mario Duarte
 *
 */
public class WikiFeedHandlerTest extends BaseWikiServiceTest {

	@Test
	@org.junit.Ignore
	public void testWiki() {
		String wikiFilePath = "wiki.xml";
		IFeedHandler<Wiki> wikiFeedHandler = wikiService.getWikiFeedHandler();
		
		Wiki wiki = wikiFeedHandler.createEntity(
				createResponseFromResource(wikiFilePath));
		
		assertEquals("Private Wiki", wiki.getTitle());
		assertEquals("urn:lsid:ibm.com:td:5ecb2b42-fce0-4a4b-9132-9f5b781db15a", wiki.getId());
		assertEquals("5ecb2b42-fce0-4a4b-9132-9f5b781db15a", wiki.getUuid());
		assertEquals("Private Community (wiki members)", wiki.getLabel());
		assertFalse(wiki.isCommunityWiki());
		
		assertNotNull(wiki.getAuthor());
		assertEquals("Frank Adams", wiki.getAuthor().getName());
		assertEquals("0EE5A7FA-3434-9A59-4825-7A7000278DAA", wiki.getAuthor().getId());
		assertEquals("FrankAdams@renovations.com", wiki.getAuthor().getEmail());
		assertEquals("active", wiki.getAuthor().getUserState());
		
		assertNotNull(wiki.getModifier());
		assertEquals("Frank Adams", wiki.getModifier().getName());
		assertEquals("0EE5A7FA-3434-9A59-4825-7A7000278DAA", wiki.getModifier().getId());
		assertEquals("FrankAdams@renovations.com", wiki.getModifier().getEmail());
		assertEquals("active", wiki.getModifier().getUserState());
		
		assertEquals("2013-10-08T07:14:08.000Z", DateSerializer.toString(wiki.getPublished()));
		assertEquals("2013-11-22T10:18:26.000Z", DateSerializer.toString(wiki.getUpdated()));
		assertEquals("2013-10-08T07:14:08.000Z", DateSerializer.toString(wiki.getCreated()));
		assertEquals("2013-11-22T10:18:04.000Z", DateSerializer.toString(wiki.getModified()));
		
		assertEquals("Small description of this private wiki.", wiki.getSummary());
		
		Set<String> tags = new HashSet<String>();
		tags.add("test");
		tags.add("private");
		assertEquals(toSet(tags), toSet(wiki.getTags()));
		
		Set<String> permissions = new HashSet<String>();
		permissions.add("AddChild"); permissions.add("Delete"); permissions.add("Purge");
		permissions.add("EditProperties"); permissions.add("EditContent"); permissions.add("Edit"); 
		permissions.add("ViewProperties"); permissions.add("ViewContent"); permissions.add("View");
		permissions.add("GrantAccess"); 
		assertEquals(permissions, wiki.getPermissions());
	}
	
	@Test
	public void testCommunityWiki() {
		String wikiFilePath = "community_wiki.xml";
		IFeedHandler<Wiki> wikiFeedHandler = wikiService.getWikiFeedHandler();
		
		Wiki wiki = wikiFeedHandler.createEntity(
				createResponseFromResource(wikiFilePath));
		
		assertTrue(wiki.isCommunityWiki());
		assertEquals("1385ec11-e8c4-475b-85b8-964e1262a372", wiki.getCommunityUuid().trim());
	}
}
