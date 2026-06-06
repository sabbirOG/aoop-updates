package com.cse.project.dto;

import java.time.LocalDateTime;

public class Requests {

    public static class UserDTO {
        private String username;
        private String email;
        private String passwordHash;
        private String avatarUrl;
        private String status;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public String getPasswordHash() { return passwordHash; }
        public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
        public String getAvatarUrl() { return avatarUrl; }
        public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class FriendshipDTO {
        private Long userId;
        private Long friendId;
        private String status;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Long getFriendId() { return friendId; }
        public void setFriendId(Long friendId) { this.friendId = friendId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class CommunityDTO {
        private Long ownerId;
        private String name;
        private String description;
        private String iconUrl;
        private boolean isPublic;

        public Long getOwnerId() { return ownerId; }
        public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public String getIconUrl() { return iconUrl; }
        public void setIconUrl(String iconUrl) { this.iconUrl = iconUrl; }
        public boolean isPublic() { return isPublic; }
        public void setPublic(boolean isPublic) { this.isPublic = isPublic; }
    }

    public static class CommunityMemberDTO {
        private Long communityId;
        private Long userId;
        private String role;

        public Long getCommunityId() { return communityId; }
        public void setCommunityId(Long communityId) { this.communityId = communityId; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    public static class ChannelDTO {
        private Long communityId;
        private String name;
        private String type;

        public Long getCommunityId() { return communityId; }
        public void setCommunityId(Long communityId) { this.communityId = communityId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
    }

    public static class ThreadDTO {
        private Long channelId;
        private Long starterUserId;
        private String title;

        public Long getChannelId() { return channelId; }
        public void setChannelId(Long channelId) { this.channelId = channelId; }
        public Long getStarterUserId() { return starterUserId; }
        public void setStarterUserId(Long starterUserId) { this.starterUserId = starterUserId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    }

    public static class MessageDTO {
        private Long threadId;
        private Long senderId;
        private String content;

        public Long getThreadId() { return threadId; }
        public void setThreadId(Long threadId) { this.threadId = threadId; }
        public Long getSenderId() { return senderId; }
        public void setSenderId(Long senderId) { this.senderId = senderId; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
    }

    public static class GameDTO {
        private String title;
        private String genre;
        private String mode;
        private int maxPlayers;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public String getMode() { return mode; }
        public void setMode(String mode) { this.mode = mode; }
        public int getMaxPlayers() { return maxPlayers; }
        public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }
    }

    public static class GameSessionDTO {
        private Long gameId;
        private Long hostId;
        private String status;

        public Long getGameId() { return gameId; }
        public void setGameId(Long gameId) { this.gameId = gameId; }
        public Long getHostId() { return hostId; }
        public void setHostId(Long hostId) { this.hostId = hostId; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    public static class GameParticipantDTO {
        private Long sessionId;
        private Long userId;
        private int score;
        private String result;

        public Long getSessionId() { return sessionId; }
        public void setSessionId(Long sessionId) { this.sessionId = sessionId; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public int getScore() { return score; }
        public void setScore(int score) { this.score = score; }
        public String getResult() { return result; }
        public void setResult(String result) { this.result = result; }
    }

    public static class LeaderboardDTO {
        private Long gameId;
        private String period;

        public Long getGameId() { return gameId; }
        public void setGameId(Long gameId) { this.gameId = gameId; }
        public String getPeriod() { return period; }
        public void setPeriod(String period) { this.period = period; }
    }

    public static class LeaderboardEntryDTO {
        private Long leaderboardId;
        private Long userId;
        private int rank;
        private int totalScore;
        private int wins;

        public Long getLeaderboardId() { return leaderboardId; }
        public void setLeaderboardId(Long leaderboardId) { this.leaderboardId = leaderboardId; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public int getRank() { return rank; }
        public void setRank(int rank) { this.rank = rank; }
        public int getTotalScore() { return totalScore; }
        public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
        public int getWins() { return wins; }
        public void setWins(int wins) { this.wins = wins; }
    }

    public static class AchievementDTO {
        private Long userId;
        private String title;
        private String description;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public static class TournamentDTO {
        private Long gameId;
        private Long organizerId;
        private String name;
        private String status;
        private int prizePool;
        private LocalDateTime startDate;
        private LocalDateTime endDate;

        public Long getGameId() { return gameId; }
        public void setGameId(Long gameId) { this.gameId = gameId; }
        public Long getOrganizerId() { return organizerId; }
        public void setOrganizerId(Long organizerId) { this.organizerId = organizerId; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public int getPrizePool() { return prizePool; }
        public void setPrizePool(int prizePool) { this.prizePool = prizePool; }
        public LocalDateTime getStartDate() { return startDate; }
        public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
        public LocalDateTime getEndDate() { return endDate; }
        public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    }

    public static class TournamentParticipantDTO {
        private Long tournamentId;
        private Long userId;
        private Integer finalRank;

        public Long getTournamentId() { return tournamentId; }
        public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Integer getFinalRank() { return finalRank; }
        public void setFinalRank(Integer finalRank) { this.finalRank = finalRank; }
    }
}
