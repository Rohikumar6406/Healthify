import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { getActivityDetail } from '../services/api';
import { Box, Card, CardContent, Divider, Typography, CircularProgress } from '@mui/material';

const ActivityDetail = () => {
  const { id } = useParams();
  const [activity, setActivity] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchActivityDetail = async () => {
      try {
        const data = await getActivityDetail(id);
        setActivity(data);
      } catch (err) {
        console.error(err);
        setError('Failed to load activity details.');
      } finally {
        setLoading(false);
      }
    }

    fetchActivityDetail();
  }, [id]);

  if (loading) {
    return (
      <Box sx={{ display: 'flex', justifyContent: 'center', mt: 5 }}>
        <CircularProgress />
      </Box>
    );
  }

  if (error) {
    return <Typography color="error" sx={{ mt: 5, textAlign: 'center' }}>{error}</Typography>;
  }

  if (!activity) {
    return <Typography sx={{ mt: 5, textAlign: 'center' }}>No activity found.</Typography>;
  }

  return (
    <Box sx={{ maxWidth: 800, mx: 'auto', p: 2 }}>
      {/* Activity Details */}
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Typography variant="h5" gutterBottom>Activity Details</Typography>
          <Typography>Type: {activity.type}</Typography>
          <Typography>Duration: {activity.duration} minutes</Typography>
          <Typography>Calories Burned: {activity.caloriesBurned}</Typography>
          <Typography>Date: {activity.createdAt ? new Date(activity.createdAt).toLocaleString() : '-'}</Typography>
        </CardContent>
      </Card>

      {/* AI Recommendation */}
      {activity.recommendation && (
        <Card>
          <CardContent>
            <Typography variant="h5" gutterBottom>AI Recommendation</Typography>

            <Typography variant="h6">Analysis</Typography>
            <Typography paragraph>{activity.recommendation}</Typography>

            <Divider sx={{ my: 2 }} />

            <Typography variant="h6">Improvements</Typography>
            {activity?.improvements?.map((improvement, index) => (
              <Typography key={index} paragraph>• {improvement}</Typography>
            ))}

            <Divider sx={{ my: 2 }} />

            <Typography variant="h6">Suggestions</Typography>
            {activity?.suggestions?.map((suggestion, index) => (
              <Typography key={index} paragraph>• {suggestion}</Typography>
            ))}

            <Divider sx={{ my: 2 }} />

            <Typography variant="h6">Safety Guidelines</Typography>
            {activity?.safety?.map((safety, index) => (
              <Typography key={index} paragraph>• {safety}</Typography>
            ))}

          </CardContent>
        </Card>
      )}
    </Box>
  );
};

export default ActivityDetail;
