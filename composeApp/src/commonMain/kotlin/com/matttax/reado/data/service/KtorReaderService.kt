package com.matttax.reado.data.service

import com.matttax.reado.data.network.Envelope
import com.matttax.reado.data.network.toResponse
import com.matttax.reado.data.service.ask.AskError
import com.matttax.reado.data.service.ask.AskRequest
import com.matttax.reado.data.service.ask.AskResponse
import com.matttax.reado.data.service.ask.AskResult
import com.matttax.reado.data.service.get_history.GetHistoryError
import com.matttax.reado.data.service.get_history.GetHistoryResponse
import com.matttax.reado.data.service.get_history.GetHistoryResult
import com.matttax.reado.data.service.get_next_parts.GetNextPartsError
import com.matttax.reado.data.service.get_next_parts.GetNextPartsRequest
import com.matttax.reado.data.service.get_next_parts.GetNextPartsResponse
import com.matttax.reado.data.service.get_next_parts.GetNextPartsResult
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorError
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorRequest
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorResponse
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorResult
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryError
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryRequest
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryResponse
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryResult
import com.matttax.reado.data.service.process.ProcessError
import com.matttax.reado.data.service.process.ProcessRequest
import com.matttax.reado.data.service.process.ProcessResponse
import com.matttax.reado.data.service.process.ProcessResult
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody

class KtorReaderService(
  private val client: HttpClient,
) : ReaderService {

  override suspend fun process(request: ProcessRequest): ProcessResponse =
    client.post("v1/reader/process") {
      setBody(request)
    }.body<Envelope<ProcessResult, ProcessError>>().toResponse()

  override suspend fun getNextParts(request: GetNextPartsRequest): GetNextPartsResponse =
    client.get("v1/reader/articles/${request.articleId}/next-parts") {
      parameter("last_part_index", request.lastPartIndex)
    }.body<Envelope<GetNextPartsResult, GetNextPartsError>>().toResponse()

  override suspend fun getHistory(): GetHistoryResponse =
    client.get("v1/reader/history")
      .body<Envelope<GetHistoryResult, GetHistoryError>>()
      .toResponse()

  override suspend fun getQAHistory(request: GetQAHistoryRequest): GetQAHistoryResponse =
    client.get("v1/reader/articles/${request.articleId}/qa-history")
      .body<Envelope<GetQAHistoryResult, GetQAHistoryError>>()
      .toResponse()

  override suspend fun ask(request: AskRequest): AskResponse =
    client.post("v1/reader/articles/${request.articleId}/ask") {
      setBody(request)
    }.body<Envelope<AskResult, AskError>>().toResponse()

  override suspend fun getPartByAnchor(request: GetPartByAnchorRequest): GetPartByAnchorResponse =
    client.get("v1/reader/articles/${request.articleId}/part-by-anchor") {
      parameter("anchor", request.anchor)
    }.body<Envelope<GetPartByAnchorResult, GetPartByAnchorError>>().toResponse()
}
