package com.skrg.sekoraga.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;

/**
 * Utility class for handling pagination.
 *
 * <p>
 * Pagination uses the same principles as the
 * <a href="https://developer.github.com/v3/#pagination">GitHub API</a>,
 * and follow <a href="http://tools.ietf.org/html/rfc5988">RFC 5988 (Link
 * header)</a>.
 */
public final class PaginationUtil {

    private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
    private static final String HEADER_LINK_FORMAT = "<{0}>; rel=\"{1}\"";

    private PaginationUtil() {
    }

    /**
     * Generate pagination headers for a Spring Data
     * {@link org.springframework.data.domain.Page} object.
     *
     * @param uriBuilder The URI builder.
     * @param page       The page.
     * @param <T>        The type of object.
     * @return http header.
     */
    public static <T> HttpHeaders generatePaginationHttpHeaders(UriComponentsBuilder uriBuilder, Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        int pageNumber = page.getNumber();
        int pageSize = page.getSize();
        StringBuilder link = new StringBuilder();
        if (pageNumber < page.getTotalPages() - 1) {
            link.append(prepareLink(uriBuilder, pageNumber + 1, pageSize, "next"))
                    .append(",");
        }
        if (pageNumber > 0) {
            link.append(prepareLink(uriBuilder, pageNumber - 1, pageSize, "prev"))
                    .append(",");
        }
        link.append(prepareLink(uriBuilder, page.getTotalPages() - 1, pageSize, "last"))
                .append(",")
                .append(prepareLink(uriBuilder, 0, pageSize, "first"));
        headers.add(HttpHeaders.LINK, link.toString());
        return headers;
    }

    private static String prepareLink(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize, String relType) {
        return MessageFormat.format(HEADER_LINK_FORMAT, preparePageUri(uriBuilder, pageNumber, pageSize), relType);
    }

    private static String preparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder.replaceQueryParam("page", Integer.toString(pageNumber))
                .replaceQueryParam("size", Integer.toString(pageSize))
                .toUriString()
                .replace(",", "%2C")
                .replace(";", "%3B");
    }

    public static Pageable validateSortProperties(Pageable pageable, Class<?> entityClass) {
        Sort filteredSort = Sort.by(
                pageable.getSort().stream()
                        .filter(order -> {
                            try {
                                entityClass.getDeclaredField(order.getProperty());
                                return true; // Properti valid
                            } catch (NoSuchFieldException e) {
                                return false; // Properti tidak valid
                            }
                        })
                        .toList());

        // Fallback ke default jika sort kosong
        if (filteredSort.isEmpty()) {
            filteredSort = Sort.by(Sort.Order.asc("id")); // Ganti "defaultProperty" sesuai kebutuhan
        }

        // Buat instance baru dari PageRequest
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), filteredSort);
    }
}
