package org.springframework.data.elasticsearch.client.reactive;

import java.io.IOException;
import java.util.function.Function;

import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.close.CloseIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.open.OpenIndexRequest;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.template.delete.DeleteIndexTemplateRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.MultiGetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.main.MainRequest;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.indices.GetFieldMappingsRequest;
import org.elasticsearch.client.indices.GetIndexTemplatesRequest;
import org.elasticsearch.client.indices.IndexTemplatesExistRequest;
import org.elasticsearch.client.indices.PutIndexTemplateRequest;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.data.elasticsearch.UncategorizedElasticsearchException;
import org.springframework.data.elasticsearch.client.util.RequestConverters;

/**
 * @author Roman Puchkovskiy
 * @author Farid Faoudi
 * @author George Popides
 * @since 4.0
 */
public interface RequestCreator {

	default Function<SearchRequest, Request> search() {
		return RequestConverters::search;
	}

	default Function<SearchScrollRequest, Request> scroll() {
		return RequestConverters::searchScroll;
	}

	default Function<ClearScrollRequest, Request> clearScroll() {
		return RequestConverters::clearScroll;
	}

	default Function<IndexRequest, Request> index() {
		return RequestConverters::index;
	}

	default Function<GetRequest, Request> get() {
		return RequestConverters::get;
	}

	default Function<MainRequest, Request> ping() {
		return (request) -> RequestConverters.ping();
	}

	default Function<MainRequest, Request> info() {
		return (request) -> RequestConverters.info();
	}

	default Function<MultiGetRequest, Request> multiGet() {
		return RequestConverters::multiGet;
	}

	default Function<GetRequest, Request> exists() {
		return RequestConverters::exists;
	}

	default Function<UpdateRequest, Request> update() {
		return RequestConverters::update;
	}

	default Function<DeleteRequest, Request> delete() {
		return RequestConverters::delete;
	}

	default Function<DeleteByQueryRequest, Request> deleteByQuery() {
		return RequestConverters::deleteByQuery;
	}

	/**
	 * @since 4.2
	 */
	default Function<UpdateByQueryRequest, Request> updateByQuery() {
		return RequestConverters::updateByQuery;
	}

	default Function<BulkRequest, Request> bulk() {

		return request -> {

			try {
				return RequestConverters.bulk(request);
			} catch (IOException e) {
				throw new UncategorizedElasticsearchException("Could not parse request", e);
			}
		};
	}

	// --> INDICES

	default Function<GetIndexRequest, Request> indexExists() {
		return RequestConverters::indexExists;
	}

	default Function<DeleteIndexRequest, Request> indexDelete() {
		return RequestConverters::indexDelete;
	}

	default Function<CreateIndexRequest, Request> indexCreate() {
		return RequestConverters::indexCreate;
	}

	default Function<OpenIndexRequest, Request> indexOpen() {
		return RequestConverters::indexOpen;
	}

	default Function<CloseIndexRequest, Request> indexClose() {
		return RequestConverters::indexClose;
	}

	default Function<RefreshRequest, Request> indexRefresh() {
		return RequestConverters::indexRefresh;
	}

	default Function<PutMappingRequest, Request> putMapping() {
		return RequestConverters::putMapping;
	}

	default Function<FlushRequest, Request> flushIndex() {
		return RequestConverters::flushIndex;
	}

	default Function<CountRequest, Request> count() {
		return RequestConverters::count;
	}

	/**
	 * @since 4.1
	 */
	default Function<GetSettingsRequest, Request> getSettings() {
		return RequestConverters::getSettings;
	}

	/**
	 * @since 4.1
	 */
	default Function<GetMappingsRequest, Request> getMapping() {
		return RequestConverters::getMapping;
	}

	/**
	 * @since 4.1
	 */
	default Function<IndicesAliasesRequest, Request> updateAlias() {
		return RequestConverters::updateAliases;
	}

	/**
	 * @since 4.1
	 */
	default Function<GetAliasesRequest, Request> getAlias() {
		return RequestConverters::getAlias;
	}

	/**
	 * @since 4.1
	 */
	default Function<PutIndexTemplateRequest, Request> putTemplate() {
		return RequestConverters::putTemplate;
	}

	/**
	 * @since 4.1
	 */
	default Function<GetIndexTemplatesRequest, Request> getTemplates() {
		return RequestConverters::getTemplates;
	}

	/**
	 * @since 4.1
	 */
	default Function<IndexTemplatesExistRequest, Request> templatesExist() {
		return RequestConverters::templatesExist;
	}

	/**
	 * @since 4.1
	 */
	default Function<DeleteIndexTemplateRequest, Request> deleteTemplate() {
		return RequestConverters::deleteTemplate;
	}

	/**
	 * @since 4.2
	 */
	default Function<GetFieldMappingsRequest, Request> getFieldMapping() {
		return RequestConverters::getFieldMapping;
	}

	/**
	 * @since 4.2
	 */
	default Function<org.elasticsearch.client.indices.GetIndexRequest, Request> getIndex() {
		return RequestConverters::getIndex;
	}
}
