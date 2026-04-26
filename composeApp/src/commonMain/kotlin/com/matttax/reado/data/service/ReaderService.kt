package com.matttax.reado.data.service

import com.matttax.reado.data.service.ask.AskRequest
import com.matttax.reado.data.service.ask.AskResponse
import com.matttax.reado.data.service.get_history.GetHistoryResponse
import com.matttax.reado.data.service.get_next_parts.GetNextPartsRequest
import com.matttax.reado.data.service.get_next_parts.GetNextPartsResponse
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorRequest
import com.matttax.reado.data.service.get_part_by_anchor.GetPartByAnchorResponse
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryRequest
import com.matttax.reado.data.service.get_qa_history.GetQAHistoryResponse
import com.matttax.reado.data.service.process.ProcessRequest
import com.matttax.reado.data.service.process.ProcessResponse

interface ReaderService {
  suspend fun process(request: ProcessRequest): ProcessResponse
  suspend fun getNextParts(request: GetNextPartsRequest): GetNextPartsResponse
  suspend fun getHistory(): GetHistoryResponse
  suspend fun getQAHistory(request: GetQAHistoryRequest): GetQAHistoryResponse
  suspend fun ask(request: AskRequest): AskResponse
  suspend fun getPartByAnchor(request: GetPartByAnchorRequest): GetPartByAnchorResponse
}
